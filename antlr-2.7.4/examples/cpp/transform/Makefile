###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := calc.g
calc_g_FILES := CalcLexer.cpp CalcLexer.hpp \
	CalcParser.cpp CalcParser.hpp \
	CalcParserTokenTypes.hpp CalcParserTokenTypes.txt \
	CalcTreeWalker.cpp CalcTreeWalker.hpp

B_NAME := calc
B_OBJS := CalcLexer.o CalcParser.o CalcTreeWalker.o Main.o

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

CalcLexer.o: CalcLexer.hpp CalcParserTokenTypes.hpp
CalcParser.o: CalcParser.hpp CalcParserTokenTypes.hpp
CalcTreeWalker.o: CalcTreeWalker.hpp CalcParserTokenTypes.hpp
Main.o: CalcLexer.hpp CalcParserTokenTypes.hpp CalcParser.hpp
Main.o: CalcTreeWalker.hpp
