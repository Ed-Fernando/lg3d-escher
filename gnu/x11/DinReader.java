/**
 * New for Project Looking Glass
 *
 * $RCSfile: DinReader.java,v $
 *
 * Copyright (c) 2004, Sun Microsystems, Inc., All Rights Reserved
 *
 * Redistributions in source code form must reproduce the above
 * copyright and this condition.
 *
 * The contents of this file are subject to the GNU General Public
 * License, Version 2 (the "License"); you may not use this file
 * except in compliance with the License. A copy of the License is
 * available at http://www.opensource.org/licenses/gpl-license.php.
 *
 * $Revision: 1.9 $
 * $Date: 2007-08-22 14:54:08 $
 * $State: Exp $
 */

package gnu.x11;

import java.io.IOException;
import java.io.EOFException;
import java.util.LinkedList;
import java.util.ListIterator;
import gnu.x11.Display;
import gnu.x11.Error;
import gnu.x11.event.Event;

class DinReader
    implements Runnable
{
    // Enable this to print out when DinReader is ignoring an error
    private static final boolean verboseIgnore = false;

    private static final String threadNameDefault = "Escher-DinReader";

    private Display display;
    private EscherDataInputStream din;
    private String clientThreadName = null;
    private String threadName;

    private LinkedList replies = new LinkedList();
    private LinkedList events = new LinkedList();
    private LinkedList errorsToIgnore = null;

    private boolean threadStopped;
    private Display.DisconnectListener disconnectListener;

    public DinReader (Display display, EscherDataInputStream din) {
	this.display = display;
        this.din = din;
    }

    public DinReader (Display display, EscherDataInputStream din, String clientThreadName) {
	this.display = display;
        this.din = din;
	this.clientThreadName = clientThreadName;
    }

    public void setDisconnectListener (Display.DisconnectListener listener) {
	disconnectListener = listener;
    }

    // Start the thread
    public void startReading () {
	threadName = threadNameDefault;
	if (clientThreadName != null) {
	    threadName += "-For-" + clientThreadName;
	}
	(new Thread(this, threadName)).start();
    }

    public void run () {
	threadStopped = false;
	try {
	    while (!threadStopped) {
		Data message = readMessage();

		int replyType = message.read1(0);
		if (replyType == XProtocolInfo.X_REPLY) {
		    // It's a reply
		    postReply(message);
		} else {
		    // It's an event
		    postEvent(message);
		}
	    }
	} catch (EOFException e) {
	    // This happens normally when a Wonderland app's window manager goes away
	    System.err.println("DinReader: saw EOF exception");
	    threadStopped = true;
	} catch (IOException e) {
	    // This happens normally when a Wonderland app's window manager goes away
	    System.err.println("DinReader: saw IO exception");
	    threadStopped = true;
	}

	if (disconnectListener != null) {
	    disconnectListener.disconnected();
	}
    }

    public void stop () {
	threadStopped = true;
    }

    private Data readMessage () throws EOFException, IOException {
	Data message = null;
	while (message == null) {
	    try {
		message = MessageFactory.build (display);
	    } catch (Error error) {

		// Should we ignore the error?
		boolean ignore = false;
		if (errorsToIgnore != null) {
		    ListIterator it = errorsToIgnore.listIterator();
		    while (it.hasNext()) {
			Integer errCodeToIgnore = (Integer) it.next();
			if (error.code == errCodeToIgnore.intValue()) {
			    ignore = true;
			    break;
			}
		    }
		}

		// Determine whether the error's request is synchronous or asynchronous
		int replySize = XProtocolInfo.getRequestReplySize(
				      error.major_opcode, error.minor_opcode);
		boolean synchronous = (replySize != 0);
		//System.err.println("replySize = " + replySize);
		//System.err.println("synchronous = " + synchronous);

		// Debug code
		if (ignore && verboseIgnore) {
		    System.err.println("Escher DinReader: Ignoring exception: ");
		    System.err.println(error);
		}

		if (synchronous) {
		    if (ignore) {

			// Case 1: Synchronous and ignored: return a dummy reply 
			// of the appropriate size in the reply queue.
			message = new DummyReply(error, replySize);

		    } else {

			// Case 2: Synchronous and not ignored: return an error reply 
			// in the reply queue
			message = new ErrorReply(error);
		    }
		} else {
		    if (ignore) {

			// Case 3: Asynchronous and ignored: do nothing and try to 
			// get the next message
			continue;

		    } else {

			// Case 4: Asynchronous and not ignored: rethrow the error
			throw error;
		    }
		}
	    }
	} 

	return message;
    }
   
    
    private synchronized void postReply (Data message) {
	//if (message != null) {
	//    System.err.println("postReply: posting " + message.read2(2));
	//}
	replies.add(message);
	notifyAll();
    }

    private synchronized void postEvent (Data message) {
        events.add((Event)message);
	notifyAll();
    }

    public synchronized Data readReply () {
	Data reply = null;

	try {
	    // Wait until there is a reply
	    while (replies.size() <= 0 && !threadStopped) {
		wait();
	    }
	} catch (InterruptedException ie) {
	    System.err.println("Error: DinReader.readReply: interrupted, exception = " + ie);
	}

	if (threadStopped) {
	    System.err.println("DinReader thread stopped");
	    return null;
	}

	reply = (Data) replies.getFirst();
	replies.removeFirst();

	//if (reply != null) {
	//    System.err.println("readReply: reading " + reply.read2(2));
	//}

	return reply;
    }

    // Return the next event.
    //
    // If block is false, immediately return null if there are no events.
    // If block is true and there are no events, block the thread until
    // an event arrives.
    //
    // If remove is true, remove the returned event from the queue. Otherwise
    // leave it in the queue.
    //
    // Note: the caller must first flush requests before calling this method.

    public synchronized Event readEvent (boolean block, boolean remove) {
	Event event = null;

	// See if there are any events
	if (events.size() <= 0) {
	    if (!block) return null;
	}

	try {
	    // Wait until there is an event
	    while (events.size() <= 0 && !threadStopped) {
		wait();
	    }
	} catch (InterruptedException ie) {
	    System.err.println("Error: DinReader.readEvent: interrupted, exception = " + ie);
	}

	if (threadStopped) {
	    System.err.println("DinReader thread stopped");
	    return null;
	}

	event = (Event) events.getFirst();

	if (remove) {
	    events.removeFirst();
	}

	return event;
    }

    // Check all of the existing events currently in the event queue 
    // to see if there is an event of a specific code and with a 
    // specific window ID.
    //
    // True is returned if such an event is in the queue.
    // This routine makes no changes to the event queue.

    public synchronized boolean checkEventTypeWindow (int code, int wid) {        

	ListIterator it = events.listIterator();
	while (it.hasNext()) {
	    Event event = (Event) it.next();
	    if (event.code() == code && event.window_id() == wid) {
		return true;
	    }                
	}
	
        return false;
    }

    // Skips all bytes on din which are available to be read

    public synchronized void skipAllAvailableBytes () 
	throws IOException
    {
        din.skip(din.available());
    }

    // TODO: windows sometimes appear and disappear so fast that 
    // LG ends up sending some requests after a window has been 
    // destroyed. As a simple work around the LG Display Server
    // is simply going to tell Escher to ignore troublesome requests.
    // But we should eventually see if there is a better way to work around.

    public synchronized void ignoreError (int errCode) {
	if (errorsToIgnore == null) {
	    errorsToIgnore = new LinkedList();
	}
	errorsToIgnore.add(new Integer(errCode));
    }

    // Deletes all replies from the reply queue

    public void clearReplies () {
	replies.clear();
    }

}

class DummyReply extends Data {
    public DummyReply (Error error, int replySize) {
        super(replySize);
        this.write1(XProtocolInfo.X_REPLY); 
        this.write1(0);
        this.write2(error.seq_no);
    }
}

class ErrorReply extends Data {
    private Error error;
    
    public ErrorReply(Error error) {
        super(32);
        this.error = error;
        this.write1(XProtocolInfo.X_REPLY); 
        this.write1(0);
        this.write2(error.seq_no);
    }
    
    public Error getError(){
        return error;
    }
}

