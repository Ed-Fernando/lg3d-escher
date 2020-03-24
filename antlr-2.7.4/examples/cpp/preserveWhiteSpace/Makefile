###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := instr.g

instr_g_FILES := InstrLexer.cpp InstrLexer.hpp \
	InstrParser.cpp InstrParser.hpp \
	InstrParserTokenTypes.hpp InstrParserTokenTypes.txt \
	InstrTreeWalker.cpp InstrTreeWalker.hpp

B_NAME := preserve
B_OBJS := Main.o InstrLexer.o InstrParser.o InstrTreeWalker.o

C_TARGETS := $(instr_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

InstrLexer.o: InstrLexer.hpp InstrParserTokenTypes.hpp InstrParser.hpp
InstrParser.o: InstrParser.hpp InstrParserTokenTypes.hpp
InstrTreeWalker.o: InstrTreeWalker.hpp InstrParserTokenTypes.hpp
Main.o: InstrLexer.hpp InstrParserTokenTypes.hpp InstrParser.hpp
Main.o: InstrTreeWalker.hpp
