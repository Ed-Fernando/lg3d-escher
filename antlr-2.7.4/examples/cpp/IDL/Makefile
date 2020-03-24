###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := idl.g
idl_g_FILES := IDLLexer.cpp IDLLexer.hpp \
	IDLParser.cpp IDLParser.hpp \
	IDLTokenTypes.hpp IDLTokenTypes.txt

B_NAME := idl
B_OBJS := Main.o IDLLexer.o IDLParser.o

C_TARGETS := $(idl_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

IDLLexer.o: IDLLexer.hpp IDLTokenTypes.hpp
IDLParser.o: IDLParser.hpp IDLTokenTypes.hpp
Main.o: IDLLexer.hpp IDLTokenTypes.hpp IDLParser.hpp
