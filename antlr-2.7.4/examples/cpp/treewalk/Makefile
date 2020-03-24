###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := treewalk.g
treewalk_g_FILES := LangLexer.cpp LangLexer.hpp \
	LangParser.cpp LangParser.hpp \
	LangParserTokenTypes.hpp LangParserTokenTypes.txt \
	LangWalker.cpp LangWalker.hpp

B_NAME := treewalk
B_OBJS := LangLexer.o LangParser.o LangWalker.o Main.o

C_TARGETS := $(treewalk_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

LangLexer.o: LangLexer.hpp LangParserTokenTypes.hpp
LangParser.o: LangParser.hpp LangParserTokenTypes.hpp
LangWalker.o: LangWalker.hpp LangParserTokenTypes.hpp
Main.o: LangLexer.hpp LangParserTokenTypes.hpp LangParser.hpp LangWalker.hpp
