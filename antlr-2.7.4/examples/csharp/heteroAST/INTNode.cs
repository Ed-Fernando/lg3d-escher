using System;
using System.IO;
using antlr;
using antlr.collections;

/** A simple node to represent an INT */
public class INTNode : CalcAST {
	int v=0;

	public INTNode() {
	}

	public INTNode(Token tok) {
		v = Convert.ToInt32(tok.getText());
	}

	/** Compute value of subtree; this is heterogeneous part :) */
	public override int Value() {
		return v;
	}

	public override string ToString() {
		return " "+v;
	}

	public override void xmlSerializeNode(TextWriter outWriter) {
		outWriter.Write("<int>"+v+"</int>");
	}
}
