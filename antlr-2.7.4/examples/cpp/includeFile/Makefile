###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := P.g
P_g_FILES := PLexer.cpp PLexer.hpp PParser.cpp PParser.hpp \
	PParserTokenTypes.hpp PParserTokenTypes.txt

B_NAME := include
B_OBJS := Main.o PLexer.o PParser.o

C_TARGETS := $(P_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

Main.o: Main.hpp PLexer.hpp PParserTokenTypes.hpp PParser.hpp
PLexer.o: PLexer.hpp PParserTokenTypes.hpp Main.hpp PParser.hpp
PParser.o: PParser.hpp PParserTokenTypes.hpp Main.hpp
