package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: SynPredBlock.java,v 1.1.1.1 2004-12-13 19:08:51 deronj Exp $
 */

class SynPredBlock extends AlternativeBlock {

    public SynPredBlock(Grammar g) {
        super(g);
    }

    public SynPredBlock(Grammar g, Token start) {
        super(g, start, false);
    }

    public void generate() {
        grammar.generator.gen(this);
    }

    public Lookahead look(int k) {
        return grammar.theLLkAnalyzer.look(k, this);
    }

    public String toString() {
        return super.toString() + "=>";
    }
}
