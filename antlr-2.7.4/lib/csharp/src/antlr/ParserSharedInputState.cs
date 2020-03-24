using System;

namespace antlr
{
	/*ANTLR Translator Generator
	* Project led by Terence Parr at http://www.jGuru.com
	* Software rights: http://www.antlr.org/license.html
	*
	* $Id: ParserSharedInputState.cs,v 1.1.1.1 2004-12-13 19:08:51 deronj Exp $
	*/

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//
	
	/*This object contains the data associated with an
	*  input stream of tokens.  Multiple parsers
	*  share a single ParserSharedInputState to parse
	*  the same stream of tokens.
	*/

	public class ParserSharedInputState
	{
		/*Where to get token objects */
		protected internal TokenBuffer input;
		
		/*Are we guessing (guessing>0)? */
		public int guessing = 0;
		
		/*What file (if known) caused the problem? */
		protected internal string filename;
		
		public virtual void  reset()
		{
			guessing = 0;
			filename = null;
			input.reset();
		}
	}
}