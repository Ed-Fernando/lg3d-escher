###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := rewrite.g

rewrite_g_FILES := Rewrite.cpp Rewrite.hpp \
	RewriteTokenTypes.hpp RewriteTokenTypes.txt

B_NAME := rewrite
B_OBJS := Main.o Rewrite.o

C_TARGETS := $(rewrite_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

Main.o: Rewrite.hpp RewriteTokenTypes.hpp
Rewrite.o: Rewrite.hpp RewriteTokenTypes.hpp
