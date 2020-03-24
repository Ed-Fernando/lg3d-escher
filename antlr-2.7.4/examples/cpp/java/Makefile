###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:09:20 deronj Exp $
###############################################################################

G_FILES := java.g  java.tree.g

java_g_FILES := JavaLexer.cpp JavaLexer.hpp \
	JavaRecognizer.cpp JavaRecognizer.hpp \
	JavaTokenTypes.hpp JavaTokenTypes.txt

java_tree_g_FILES := JavaTreeParser.cpp JavaTreeParser.hpp \
	JavaTreeParserTokenTypes.hpp JavaTreeParserTokenTypes.txt

B_NAME := java
B_OBJS := Main.o JavaLexer.o JavaRecognizer.o JavaTreeParser.o

C_TARGETS := $(java_g_FILES) $(java_tree_g_FILES)

ifndef TOPDIR
TOPDIR  = $(shell pwd)/../../..
endif

include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

# Antlr deps.
EXTRA_LIBS := $(ANTLR_LIBS)
EXTRA_CXXFLAGS := $(ANTLR_INCS)

# DO NOT DELETE

JavaLexer.o: JavaLexer.hpp JavaTokenTypes.hpp
JavaRecognizer.o: JavaRecognizer.hpp JavaTokenTypes.hpp
JavaTreeParser.o: JavaTreeParser.hpp JavaTreeParserTokenTypes.hpp
Main.o: JavaLexer.hpp JavaTokenTypes.hpp JavaRecognizer.hpp
Main.o: JavaTreeParser.hpp JavaTreeParserTokenTypes.hpp
