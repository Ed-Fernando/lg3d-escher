###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := javalex.g javadoclex.g javadocparse.g javaparse.g

javalex_g_FILES := DemoJavaLexer.cpp DemoJavaLexer.hpp \
	JavaTokenTypes.hpp JavaTokenTypes.txt
javaparse_g_FILES := DemoJavaParser.cpp DemoJavaParser.hpp \
	DemoJavaParserTokenTypes.hpp DemoJavaParserTokenTypes.txt
javadoclex_g_FILES := DemoJavaDocLexer.cpp DemoJavaDocLexer.hpp \
	JavaDocTokenTypes.hpp JavaDocTokenTypes.txt
javadocparse_g_FILES := DemoJavaDocParser.cpp DemoJavaDocParser.hpp \
	DemoJavaDocParserTokenTypes.hpp DemoJavaDocParserTokenTypes.txt

B_NAME := multilex
B_OBJS := DemoJavaDocLexer.o DemoJavaDocParser.o \
	DemoJavaLexer.o DemoJavaParser.o \
	Main.o

C_TARGETS := $(javalex_g_FILES) $(javaparse_g_FILES) \
	$(javadoclex_g_FILES) $(javadocparse_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

DemoJavaDocLexer.o: DemoJavaDocLexer.hpp JavaDocTokenTypes.hpp
DemoJavaDocParser.o: DemoJavaDocParser.hpp DemoJavaDocParserTokenTypes.hpp
DemoJavaLexer.o: DemoJavaLexer.hpp JavaTokenTypes.hpp
DemoJavaParser.o: DemoJavaParser.hpp DemoJavaParserTokenTypes.hpp
DemoJavaParser.o: DemoJavaDocParser.hpp DemoJavaDocParserTokenTypes.hpp
Main.o: DemoJavaLexer.hpp JavaTokenTypes.hpp DemoJavaDocLexer.hpp
Main.o: JavaDocTokenTypes.hpp DemoJavaParser.hpp DemoJavaParserTokenTypes.hpp
