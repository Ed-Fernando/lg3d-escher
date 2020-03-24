###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := calc.g
calc_g_FILES := CalcLexer.cpp CalcLexer.hpp \
	CalcParser.cpp CalcParser.hpp \
	CalcParserTokenTypes.hpp CalcParserTokenTypes.txt

B_NAME := calc
B_OBJS := CalcLexer.o CalcParser.o Main.o

C_TARGETS := $(calc_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

CalcLexer.o: CalcLexer.hpp CalcParserTokenTypes.hpp PLUSNode.hpp
CalcLexer.o: BinaryOperatorAST.hpp CalcAST.hpp MULTNode.hpp INTNode.hpp
CalcParser.o: CalcParser.hpp CalcParserTokenTypes.hpp PLUSNode.hpp
CalcParser.o: BinaryOperatorAST.hpp CalcAST.hpp MULTNode.hpp INTNode.hpp
Main.o: CalcLexer.hpp CalcParserTokenTypes.hpp PLUSNode.hpp
Main.o: BinaryOperatorAST.hpp CalcAST.hpp MULTNode.hpp INTNode.hpp
Main.o: CalcParser.hpp
