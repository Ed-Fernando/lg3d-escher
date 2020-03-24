###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

FLEX = flex
FLEX_LIB = -lfl

G_FILES = java.g  java.tree.g

java_g_FILES := JavaRecognizer.cpp JavaRecognizer.hpp JavaTokenTypes.hpp \
	JavaTokenTypes.txt
java_tree_g_FILES := JavaTreeParser.cpp JavaTreeParser.hpp \
	JavaTreeParserTokenTypes.hpp JavaTreeParserTokenTypes.txt

B_NAME := java_recognizer
B_OBJS := Main.o LexTokenStream.o JavaRecognizer.o JavaTreeParser.o lex.yy.o

lexfiles := lex.yy.c

C_TARGETS := $(java_g_FILES) $(java_tree_g_FILES) $(lexfiles)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS) $(FLEX_LIB)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# flex stuff
lex.yy.c: java.l ;
	$(FLEX) $<

# DO NOT DELETE

JavaRecognizer.o: JavaRecognizer.hpp JavaTokenTypes.hpp
JavaTreeParser.o: JavaTreeParser.hpp JavaTreeParserTokenTypes.hpp
LexTokenStream.o: LexTokenStream.hpp
Main.o: LexTokenStream.hpp JavaRecognizer.hpp JavaTokenTypes.hpp
Main.o: JavaTreeParser.hpp JavaTreeParserTokenTypes.hpp
