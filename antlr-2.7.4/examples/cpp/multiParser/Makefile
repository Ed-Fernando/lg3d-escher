###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := lexer.g parser1.g parser2.g

lexer_g_FILES := SimpleLexer.cpp  SimpleLexer.hpp \
	SimpleTokenTypes.hpp  SimpleTokenTypes.txt
parser1_g_FILES := SimpleParser.cpp SimpleParser.hpp \
	SimpleParserTokenTypes.hpp SimpleParserTokenTypes.txt
parser2_g_FILES := SimpleParser2.cpp SimpleParser2.hpp \
	SimpleParser2TokenTypes.hpp SimpleParser2TokenTypes.txt

B_NAME := vocabshare
B_OBJS := Main.o SimpleLexer.o SimpleParser.o SimpleParser2.o

C_TARGETS := $(lexer_g_FILES) $(parser1_g_FILES) $(parser2_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

Main.o: SimpleLexer.hpp SimpleTokenTypes.hpp SimpleParser.hpp
Main.o: SimpleParserTokenTypes.hpp SimpleParser2.hpp
Main.o: SimpleParser2TokenTypes.hpp
SimpleLexer.o: SimpleLexer.hpp SimpleTokenTypes.hpp
SimpleParser.o: SimpleParser.hpp SimpleParserTokenTypes.hpp
SimpleParser2.o: SimpleParser2.hpp SimpleParser2TokenTypes.hpp
