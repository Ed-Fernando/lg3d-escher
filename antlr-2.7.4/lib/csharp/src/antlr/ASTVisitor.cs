using System;

using AST		= antlr.collections.AST;

namespace antlr
{
	/* ANTLR Translator Generator
	 * Project led by Terence Parr at http://www.jGuru.com
	 * Software rights: http://www.antlr.org/license.html
	 *
	 * $Id: ASTVisitor.cs,v 1.1.1.1 2004-12-13 19:08:50 deronj Exp $
	 */

	//
	// ANTLR C# Code Generator by Micheal Jordan
	//                            Kunle Odutola       : kunle UNDERSCORE odutola AT hotmail DOT com
	//                            Anthony Oguntimehin
	//
	// With many thanks to Eric V. Smith from the ANTLR list.
	//

	/// <summary>
	/// Summary description for ASTVisitor.
	/// </summary>
	public interface ASTVisitor
	{
		void visit(AST node);
	}
}
