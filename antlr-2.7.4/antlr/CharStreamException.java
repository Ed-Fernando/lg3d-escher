package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: CharStreamException.java,v 1.1.1.1 2004-12-13 19:09:11 deronj Exp $
 */

/**
 * Anything that goes wrong while generating a stream of characters
 */
public class CharStreamException extends ANTLRException {
    /**
     * CharStreamException constructor comment.
     * @param s java.lang.String
     */
    public CharStreamException(String s) {
        super(s);
    }
}
