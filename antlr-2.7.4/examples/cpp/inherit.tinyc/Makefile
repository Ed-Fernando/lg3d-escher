###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := subc.g
subc_g_FILES := TinyCLexer.cpp TinyCLexer.hpp TinyCTokenTypes.hpp \
	TinyCTokenTypes.txt TinyCParser.hpp \
	MyCParser.cpp MyCParser.hpp \
	MyCParserTokenTypes.hpp MyCParserTokenTypes.txt \
	TinyCParserTokenTypes.txt
ANTLR_FLAGS := -glib ../tinyc/tinyc.g

B_NAME := tinyc2
B_OBJS := Main.o MyCParser.o

C_TARGETS := $(subc_g_FILES) TinyCParserTokenTypes.txt expandedsubc.g

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

TINYTOKENTYPES=../tinyc/TinyCParserTokenTypes.txt

ifneq (../tinyc/TinyCParserTokenTypes.txt,$(wildcard ../tinyc/TinyCParserTokenTypes.txt))
default: ;
	@echo "Please make tinyc example before trying this one."
else
default:
	cp ../tinyc/TinyCParserTokenTypes.txt .
	$(MAKE) $(B_NAME)
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := ../tinyc/TinyCLexer.o $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS) -I../tinyc

MyCParser.hpp MyCParserTokenTypes.hpp: .subc.g
Main.o: Main.cpp MyCParserTokenTypes.hpp
MyCParser.o: MyCParser.cpp MyCParser.hpp MyCParserTokenTypes.hpp
