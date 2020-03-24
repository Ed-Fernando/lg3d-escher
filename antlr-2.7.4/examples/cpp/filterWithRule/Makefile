###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := t.g
t_g_FILES := T.cpp T.hpp TTokenTypes.hpp TTokenTypes.txt

B_NAME := filter
B_OBJS := Test.o T.o

C_TARGETS := $(t_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

T.o: T.hpp TTokenTypes.hpp
Test.o: T.hpp TTokenTypes.hpp
