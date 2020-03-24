###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := test.g
test_g_FILES := SupportTest.cpp \
	SupportTest.hpp \
	SupportTestTokenTypes.hpp \
	SupportTestTokenTypes.txt

# Binary to generate
B_NAME := ASTSupport
# Objects it consists of
B_OBJS := SupportTest.o Main.o

C_TARGETS := $(test_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

Main.o: SupportTest.hpp SupportTestTokenTypes.hpp
SupportTest.o: SupportTest.hpp SupportTestTokenTypes.hpp
