/**
 * New for Project Looking Glass
 *
 * $RCSfile: XProtocolInfo.java,v $
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
 * $Revision: 1.2 $
 * $Date: 2004-12-14 01:19:27 $
 * $State: Exp $
 */

package gnu.x11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

// Information from the protocol specification

public class XProtocolInfo {

    public static final int X_ERROR = 0;
    public static final int X_REPLY = 1;

    // The size (in bytes) of most X request replies
    public static final int REPLY_SIZE_GENERIC = 32;

    // The start of the extension major opcodes
    public static final int EXT_OPCODE_START = 128;

    private static final int coreNonGenericReplyRequestCodes[] = {
        /* X_GetWindowAttributes        */ 3,
        /* X_QueryKeymap                */ 44,       
        /* X_QueryFont                  */ 47,
        /* X_GetKeyboardControl         */ 103             
    };

    private static final int coreNonGenericReplySizes[] = {
	/* sz_xGetWindowAttributesReply */ 44,
        /* sx_xQueryKeymapReply         */ 40,
        /* sz_xQueryFontReply           */ 60,
	/* sz_xGetKeyboardControlReply  */ 52
    };

    private static final int GetWindowAttributes          = 3;     
    private static final int GetGeometry                  = 14;
    private static final int QueryTree                    = 15;
    private static final int InternAtom                   = 16;
    private static final int GetAtomName                  = 17;
    private static final int GetProperty                  = 20;
    private static final int ListProperties               = 21; 
    private static final int GetSelectionOwner            = 23;    
    private static final int GrabPointer                  = 26;
    private static final int GrabKeyboard                 = 31;
    private static final int QueryPointer                 = 38;        
    private static final int GetMotionEvents              = 39;           
    private static final int TranslateCoords              = 40;                
    private static final int GetInputFocus                = 43;         
    private static final int QueryKeymap                  = 44;       
    private static final int QueryFont                    = 47;
    private static final int QueryTextExtents             = 48;     
    private static final int ListFonts                    = 49;  
    private static final int GetFontPath                  = 52; 
    private static final int GetImage                     = 73; 
    private static final int ListInstalledColormaps       = 83;                  
    private static final int AllocColor                   = 84;      
    private static final int AllocNamedColor              = 85;           
    private static final int AllocColorCells              = 86;           
    private static final int AllocColorPlanes             = 87;            
    private static final int QueryColors                  = 91;       
    private static final int LookupColor                  = 92;       
    private static final int QueryBestSize                = 97;         
    private static final int QueryExtension               = 98;          
    private static final int ListExtensions               = 99;          
    private static final int GetKeyboardMapping           = 101;
    private static final int GetKeyboardControl           = 103;             
    private static final int GetPointerControl            = 106;
    private static final int GetScreenSaver               = 108;          
    private static final int ListHosts                    = 110;     
    private static final int SetPointerMapping            = 116;
    private static final int GetPointerMapping            = 117;
    private static final int SetModifierMapping	         = 118;
    private static final int GetModifierMapping	         = 119;

    private static final int coreRequestsExpectingReplies[] = {
	GetWindowAttributes,
	GetGeometry,
	QueryTree,
	InternAtom,
	GetAtomName,
	GetProperty,
	ListProperties,
	GetSelectionOwner,
	GrabPointer,
	GrabKeyboard,
	QueryPointer,
	GetMotionEvents,
	TranslateCoords,
	GetInputFocus,
	QueryKeymap,
	QueryFont,
	QueryTextExtents,
	ListFonts,
	GetFontPath,
	GetImage,
	ListInstalledColormaps,
	AllocColor,
	AllocNamedColor,
	AllocColorCells,
	AllocColorPlanes,
	QueryColors,
	LookupColor,
	QueryBestSize,
	QueryExtension,
	ListExtensions,
        GetKeyboardMapping,
	GetKeyboardControl,
	GetPointerControl,
	GetScreenSaver,
	ListHosts,
        SetPointerMapping,
        GetPointerMapping,
        SetModifierMapping,
        GetModifierMapping
    };

    private static class ExtensionRequestInfo {
	int minorOpcode;
	int replySize;
    }

    private static class ExtensionInfo {
	LinkedList requestsExpectingReplies = new LinkedList();
    }

    // Extension op code -> ExtensionInfo
    private static HashMap extensionInfo = new HashMap();

    private static ExtensionInfo lookupExtensionInfo (int majorOpcode) {
	Integer extCode = new Integer(majorOpcode);
	return (ExtensionInfo) extensionInfo.get(extCode);
    }

    // All extensions which have requests which expect replies should call
    // this for each such request to specify its reply size
    // TODO: this does not handle extension requests with variable reply sizes

    public static void extensionRequestExpectsReply (int majorOpcode, int minorOpcode, 
						     int replySize) {

	// Create extension info for this extension if it doesn't yet exist
	ExtensionInfo extInfo = lookupExtensionInfo(majorOpcode);
	if (extInfo == null) {
	    extInfo = new ExtensionInfo();
	    extensionInfo.put(new Integer(majorOpcode), extInfo);
	}

	ExtensionRequestInfo reqInfo = new ExtensionRequestInfo();
	reqInfo.minorOpcode = minorOpcode;
	reqInfo.replySize = replySize;
	extInfo.requestsExpectingReplies.add(reqInfo);
    }

    private static int extGetRequestReplySize (int majorOpcode, int minorOpcode) {

	// Do we have any info about this extension? 
	ExtensionInfo extInfo = lookupExtensionInfo(majorOpcode);
	if (extInfo == null) {
	    throw new RuntimeException("Cannot find X protocol extension info for extension code " + majorOpcode);
	}

	ListIterator it = extInfo.requestsExpectingReplies.listIterator();
	while (it.hasNext()) {
	    ExtensionRequestInfo reqInfo = (ExtensionRequestInfo) it.next();
	    if (minorOpcode == reqInfo.minorOpcode) {
		return reqInfo.replySize;
	    }
	}

	return 0;
    } 

    private static int coreGetRequestReplySize (int requestCode) {

        // Does the request expect a reply?
        for (int i = 0; i < coreRequestsExpectingReplies.length; i++) {
	    if (requestCode == coreRequestsExpectingReplies[i]) {
		for (int k = 0; i < coreNonGenericReplyRequestCodes.length; i++) {
		    if (coreNonGenericReplyRequestCodes[k] == requestCode) {
			return coreNonGenericReplySizes[k];
		    }
		}
		return REPLY_SIZE_GENERIC;
	    }
	}

	return 0;
    }

    // If the given requestCode expects a reply, this routine returns its
    // reply size. The given request code does not expect a reply, 0 is returned.

    public static int getRequestReplySize (int majorOpcode, int minorOpcode) {
	if (majorOpcode >= EXT_OPCODE_START) {
	    return extGetRequestReplySize(majorOpcode, minorOpcode);
        } else {
	    return coreGetRequestReplySize(majorOpcode);
        }
    }
}
