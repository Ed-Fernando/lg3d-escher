using System;
using IOException = System.IO.IOException;
	
namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: TokenStreamIOException.cs,v 1.1.1.1 2004-12-13 19:08:51 deronj Exp $
	*/

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//

	/*
	* Wraps an IOException in a TokenStreamException
	*/
	[Serializable]
	public class TokenStreamIOException : TokenStreamException
	{
		public IOException io;
		/*
		* TokenStreamIOException constructor comment.
		* @param s java.lang.String
		*/
		public TokenStreamIOException(IOException io) : base(io.Message)
		{
			this.io = io;
		}
	}
}