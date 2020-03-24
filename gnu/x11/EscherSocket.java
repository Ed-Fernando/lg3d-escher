
/**
 * $RCSfile: EscherSocket.java,v $
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
 * $Revision: 1.1 $
 * $Date: 2005-11-15 18:49:54 $
 * $State: Exp $
 */

package gnu.x11;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A socket which uses AF_UNIX sockets when the host is local.
 */

public class EscherSocket {

    // For debug: set to true to always force the use of TCP/IP
    // sockets instead of local Unix sockets.
    private static boolean FORCE_IP = false;

    private Socket remoteSocket = null;
    private int    localSocketFd;        // The fd of the socket

    private native int socketCreateAndConnect (int displayNum) 
	throws IOException;
    private native void socketClose (int fd);

    static {
        System.loadLibrary("Escher");
    }

    public EscherSocket (String host, int displayNum, int remotePort) 
       throws UnknownHostException, IOException
    {
	//System.err.println("EscherSocket(): check host: " + host);
        if (!FORCE_IP && isLocalHost(host)) {
	    localSocketFd = socketCreateAndConnect(displayNum);
    	    //System.err.println("EscherSocket(): host is local");
	} else {
	    remoteSocket = new Socket(host, remotePort);
    	    //System.err.println("EscherSocket(): host is remote");
	}
    }

    public EscherOutputStream getOutputStream () 
	throws IOException
    {
	if (remoteSocket == null) {
	    return new EscherOutputStream(localSocketFd);
	} else {
	    return new EscherOutputStream(remoteSocket.getOutputStream());
	}
    }

    // New addition to the socket interface
    public EscherDataInputStream getDataInputStream () 
	throws IOException
    {
	if (remoteSocket == null) {
	    return new EscherDataInputStream(localSocketFd);
	} else {
	    return new EscherDataInputStream(
		new DataInputStream(remoteSocket.getInputStream()));
	}
    }

    public void close () 
	throws IOException
    {
	if (remoteSocket == null) {
	    socketClose(localSocketFd);
	} else {
	    remoteSocket.close();
	}
    }

    private boolean isLocalHost (String host) 
	throws UnknownHostException
    {
	String hostName;

	if (host == null) return true;
	
	int colonIndex = host.indexOf(":");
	if (colonIndex == -1) {
	    // Entire string is host name
	    hostName = host;
	} else {
	    hostName = host.substring(0, colonIndex-1);
	}
    
	if (hostName.length() == 0) return true;

	if (hostName.compareTo("127.0.0.1") == 0 ||
	    hostName.compareTo("localhost") == 0) {
	    return true;
	}

	// Get hostname of this host
	InetAddress ia;
	ia = InetAddress.getLocalHost();
	String iaHostName = ia.getHostName();
	if (hostName.compareTo(iaHostName) == 0) {
	    return true;
	}

	return false;
    }
}
