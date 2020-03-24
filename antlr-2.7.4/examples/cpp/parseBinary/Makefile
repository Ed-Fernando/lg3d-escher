###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := data.g

data_g_FILES := DataLexer.cpp DataLexer.hpp \
	DataParser.cpp DataParser.hpp \
	DataParserTokenTypes.hpp DataParserTokenTypes.txt

B_NAME := binparser
B_OBJS := Main.o DataLexer.o DataParser.o

C_TARGETS := $(data_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

DataLexer.o: DataLexer.hpp DataParserTokenTypes.hpp
DataParser.o: DataParser.hpp DataParserTokenTypes.hpp
Main.o: DataLexer.hpp DataParserTokenTypes.hpp DataParser.hpp
