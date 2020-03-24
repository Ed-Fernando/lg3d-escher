using System;
using CharBuffer   = antlr.CharBuffer;
using Token			 = antlr.Token;
	
class Test 
{
	public static void Main(string[] args) 
	{
		try 
		{
			T lexer = new T(new CharBuffer(Console.In));
			bool done = false;
			while ( !done ) 
			{
				Token tok = lexer.nextToken();
				Console.Out.WriteLine("Token: "+tok);
				if ( tok.Type==Token.EOF_TYPE ) {
					done = true;
				}
			}
			Console.Out.WriteLine("done lexing...");
		} 
		catch(Exception e) 
		{
			Console.Error.WriteLine("exception: "+e);
		}
	}
}

