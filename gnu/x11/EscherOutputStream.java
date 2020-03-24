
/**
 * $RCSfile: EscherOutputStream.java,v $
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

import java.io.IOException;
import java.io.OutputStream;

public class EscherOutputStream {

    private OutputStream outStream = null;
    private int          outFd;

    private native void write (int fd, byte[] b, int off, int len);

    public EscherOutputStream (OutputStream outStream) {
	this.outStream = outStream;
    }

    public EscherOutputStream (int outFd) {
	this.outFd = outFd;
    }

    public void write (byte[] b) 
	throws IOException
    {
	if (outStream == null) {
	    write(outFd, b, 0, b.length);
	} else {
	    outStream.write(b);
	}
    }

    public void write (byte[] b, int off, int len) 
	throws IOException
    {
	if (outStream == null) {
	    write(outFd, b, off, len);
	} else {
	    outStream.write(b, off, len);
	}
    }

}
