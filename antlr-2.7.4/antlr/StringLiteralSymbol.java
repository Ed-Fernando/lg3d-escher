package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: StringLiteralSymbol.java,v 1.1.1.1 2004-12-13 19:09:09 deronj Exp $
 */

class StringLiteralSymbol extends TokenSymbol {
    protected String label;	// was this string literal labeled?


    public StringLiteralSymbol(String r) {
        super(r);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
