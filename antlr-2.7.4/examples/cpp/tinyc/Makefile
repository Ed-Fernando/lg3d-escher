###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := lexer.g tinyc.g

lexer_g_FILES := TinyCLexer.cpp TinyCLexer.hpp \
	TinyCTokenTypes.hpp TinyCTokenTypes.txt
tinyc_g_FILES := TinyCParser.cpp TinyCParser.hpp \
	TinyCParserTokenTypes.hpp TinyCParserTokenTypes.txt

B_NAME := tinyc
B_OBJS := Main.o TinyCLexer.o TinyCParser.o

C_TARGETS := $(lexer_g_FILES) $(tinyc_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

Main.o: TinyCLexer.hpp TinyCTokenTypes.hpp TinyCParser.hpp
Main.o: TinyCParserTokenTypes.hpp
TinyCLexer.o: TinyCLexer.hpp TinyCTokenTypes.hpp
TinyCParser.o: TinyCParser.hpp TinyCParserTokenTypes.hpp
