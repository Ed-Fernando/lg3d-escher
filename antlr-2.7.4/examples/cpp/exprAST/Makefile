###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := expr.g
expr_g_FILES := \
	ExprLexer.cpp  ExprParser.cpp  ExprParserTokenTypes.hpp \
	ExprLexer.hpp  ExprParser.hpp  ExprParserTokenTypes.txt

B_NAME := expr
B_OBJS := Main.o ExprLexer.o ExprParser.o

C_TARGETS := $(expr_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

ExprLexer.o: ExprLexer.hpp ExprParserTokenTypes.hpp
ExprParser.o: ExprParser.hpp ExprParserTokenTypes.hpp
Main.o: ExprLexer.hpp ExprParserTokenTypes.hpp ExprParser.hpp
