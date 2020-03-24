using System;
using IOException = System.IO.IOException;

namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: CharStreamIOException.cs,v 1.1.1.1 2004-12-13 19:08:51 deronj Exp $
	*/

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//
	
	/*
	* Wrap an IOException in a CharStreamException
	*/
	[Serializable]
	public class CharStreamIOException : CharStreamException
	{
		public IOException io;
		
		public CharStreamIOException(IOException io) : base(io.Message)
		{
			this.io = io;
		}
	}
}