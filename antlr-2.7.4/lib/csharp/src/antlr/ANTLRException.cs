namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: ANTLRException.cs,v 1.1.1.1 2004-12-13 19:08:51 deronj Exp $
	*/

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//
	
	using System;
	
	[Serializable]
	public class ANTLRException : Exception
	{
		public ANTLRException() : base() 
		{
		}

		public ANTLRException(string s) : base(s) 
		{
		}

		public ANTLRException(string s, Exception inner) : base(s, inner)
		{
		}
	}
}
