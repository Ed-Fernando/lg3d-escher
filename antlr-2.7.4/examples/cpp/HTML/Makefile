###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := html.g
html_g_FILES := HTMLLexer.cpp HTMLLexer.hpp HTMLParser.cpp HTMLParser.hpp \
	HTMLTokenTypes.hpp HTMLTokenTypes.txt

B_NAME := html
B_OBJS := Main.o HTMLLexer.o HTMLParser.o

C_TARGETS := $(html_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

HTMLLexer.o: HTMLLexer.hpp HTMLTokenTypes.hpp
HTMLParser.o: HTMLParser.hpp HTMLTokenTypes.hpp
Main.o: HTMLLexer.hpp HTMLTokenTypes.hpp HTMLParser.hpp
