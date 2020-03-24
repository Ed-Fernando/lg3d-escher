/**
 * $RCSfile: EscherDataInputStream.java,v $
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
 * $Date: 2005-11-15 18:49:53 $
 * $State: Exp $
 */

package gnu.x11;

import java.io.DataInputStream;
import java.io.IOException;

public class EscherDataInputStream {

    private DataInputStream inStream;
    private int inFd;

    private native long skip (int fd, long n);
    private native int available (int fd);
    private native int readUnsignedByte (int fd) throws IOException;
    private native int readUnsignedShort (int fd) throws IOException;
    private native int read (int fd) throws IOException;
    private native void readFully (int fd, byte[] b, int off, int len)
	throws IOException;

    public EscherDataInputStream (DataInputStream inStream) {
	this.inStream = inStream;
    }

    public EscherDataInputStream (int inFd) {
	this.inFd = inFd;
    }

    public long skip (long n) 
	throws IOException
    {
	if (inStream == null) {
	    return skip(inFd, n);
	} else {
	    return inStream.skip(n);
	}
    }

    public int available () 
	throws IOException
    {
	if (inStream == null) {
	    return available(inFd);
	} else {
	    return inStream.available();
	}
    }

    public int readUnsignedByte () 
	throws IOException
    {
	if (inStream == null) {
	    return readUnsignedByte(inFd);
	} else {
	    return inStream.readUnsignedByte();
	}
    }

    public int readUnsignedShort () 
	throws IOException
    {
	if (inStream == null) {
	    return readUnsignedShort(inFd);
	} else {
	    return inStream.readUnsignedShort();
	}
    }

    public int read () 
	throws IOException
    {
	if (inStream == null) {
	    return read(inFd);
	} else {
	    return inStream.read();
	}
    }

    public void readFully(byte[] b)
	throws IOException
    {
	readFully(b, 0, b.length);
    }

    public void readFully(byte[] b, int off, int len)
	throws IOException
    {
	if (inStream == null) {
	    readFully(inFd, b, off, len);
	} else {
	    inStream.readFully(b, off, len);
	}
    }
}
