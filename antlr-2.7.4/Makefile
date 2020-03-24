###############################################################################
# $Id: Makefile,v 1.1.1.1 2004-12-13 19:08:34 deronj Exp $
###############################################################################

TARGET = antlr

DATE         := $(shell date +%Y%m%d)
VERSION_FILE := .version

ifeq ($(VERSION_FILE),$(wildcard $(VERSION_FILE)))
TAR_FILE	= $(PACKAGE_NAME)-$(DATE)-$(shell cat $(VERSION_FILE)).tar.gz
endif

TAR_DIR		 = $(shell basename $$PWD)

#
# Variable definitions
#
ifeq (scripts/Config.make,$(wildcard scripts/Config.make))
include scripts/Config.make
else
default:
	@echo "Please run configure script before running make"
	@echo "   Check ./configure --help for options"
	@echo "   Also check install.html and the documentation in doc/"
	@echo "   Please also verify that all files/directories are writeable"
endif

#
# Set TOPDIR
#
TOPDIR	=	$(shell if [ "$$PWD" != "" ]; then echo $$PWD; else pwd; fi)

#
#  JAR contents.
#
#  N.B., Any class file name with an embedded '$'
#  (javac generated filenames for inner classes) must
#  have the '$' escaped with '$' for make (i.e.,
#  each '$' in the file name is represented by '$$').
#

#
# Which classes end up in the antlr.jar (antlr.Tool)
# direct listing taken from distro antlr.jar
#
antlr_jar_FILES := \
   antlr/ANTLRError.class\
   antlr/ANTLRException.class\
   antlr/ANTLRGrammarParseBehavior.class\
   antlr/ANTLRHashString.class\
   antlr/ANTLRLexer.class\
   antlr/ANTLRParser.class\
   antlr/ANTLRStringBuffer.class\
   antlr/ANTLRTokdefLexer.class\
   antlr/ANTLRTokdefParser.class\
   antlr/ANTLRTokdefParserTokenTypes.class\
   antlr/ANTLRTokenTypes.class\
   antlr/ASTFactory.class\
   antlr/ASTIterator.class\
   antlr/ASTNULLType.class\
   antlr/ASTPair.class\
   antlr/ASTVisitor.class\
   antlr/ActionElement.class\
   antlr/ActionTransInfo.class\
   antlr/Alternative.class\
   antlr/AlternativeBlock.class\
   antlr/AlternativeElement.class\
   antlr/BaseAST.class\
   antlr/BlockContext.class\
   antlr/BlockEndElement.class\
   antlr/BlockWithImpliedExitPath.class\
   antlr/ByteBuffer.class\
   antlr/CSharpBlockFinishingInfo.class\
   antlr/CSharpCharFormatter.class\
   antlr/CSharpCodeGenerator.class\
   antlr/CSharpNameSpace.class\
   antlr/CharBuffer.class\
   antlr/CharFormatter.class\
   antlr/CharLiteralElement.class\
   antlr/CharQueue.class\
   antlr/CharRangeElement.class\
   antlr/CharScanner.class\
   antlr/CharStreamException.class\
   antlr/CharStreamIOException.class\
   antlr/CodeGenerator.class\
   antlr/CommonAST.class\
   antlr/CommonASTWithHiddenTokens.class\
   antlr/CommonHiddenStreamToken.class\
   antlr/CommonToken.class\
   antlr/CppBlockFinishingInfo.class\
   antlr/CppCharFormatter.class\
   antlr/CppCodeGenerator.class\
   antlr/DefaultFileLineFormatter.class\
   antlr/DefaultToolErrorHandler.class\
   antlr/DefineGrammarSymbols.class\
   antlr/DiagnosticCodeGenerator.class\
   antlr/DocBookCodeGenerator.class\
   antlr/DumpASTVisitor.class\
   antlr/ExceptionHandler.class\
   antlr/ExceptionSpec.class\
   antlr/FileCopyException.class\
   antlr/FileLineFormatter.class\
   antlr/Grammar.class\
   antlr/GrammarAnalyzer.class\
   antlr/GrammarAtom.class\
   antlr/GrammarElement.class\
   antlr/GrammarSymbol.class\
   antlr/HTMLCodeGenerator.class\
   antlr/ImportVocabTokenManager.class\
   antlr/InputBuffer.class\
   antlr/JavaBlockFinishingInfo.class\
   antlr/JavaCharFormatter.class\
   antlr/JavaCodeGenerator.class\
   antlr/LLkAnalyzer.class\
   antlr/LLkGrammarAnalyzer.class\
   antlr/LLkParser.class\
   antlr/LexerGrammar.class\
   antlr/LexerSharedInputState.class\
   antlr/Lookahead.class\
   antlr/MakeGrammar.class\
   antlr/MismatchedCharException.class\
   antlr/MismatchedTokenException.class\
   antlr/NameSpace.class\
   antlr/NoViableAltException.class\
   antlr/NoViableAltForCharException.class\
   antlr/OneOrMoreBlock.class\
   antlr/ParseTree.class\
   antlr/ParseTreeRule.class\
   antlr/ParseTreeToken.class\
   antlr/Parser.class\
   antlr/ParserGrammar.class\
   antlr/ParserSharedInputState.class\
   antlr/PreservingFileWriter.class\
   antlr/RecognitionException.class\
   antlr/RuleBlock.class\
   antlr/RuleEndElement.class\
   antlr/RuleRefElement.class\
   antlr/RuleSymbol.class\
   antlr/SemanticException.class\
   antlr/SimpleTokenManager.class\
   antlr/StringLiteralElement.class\
   antlr/StringLiteralSymbol.class\
   antlr/StringUtils.class\
   antlr/SynPredBlock.class\
   antlr/Token.class\
   antlr/TokenBuffer.class\
   antlr/TokenManager.class\
   antlr/TokenQueue.class\
   antlr/TokenRangeElement.class\
   antlr/TokenRefElement.class\
   antlr/TokenStream.class\
   antlr/TokenStreamBasicFilter.class\
   antlr/TokenStreamException.class\
   antlr/TokenStreamHiddenTokenFilter.class\
   antlr/TokenStreamIOException.class\
   antlr/TokenStreamRecognitionException.class\
   antlr/TokenStreamRetryException.class\
   antlr/TokenStreamRewriteEngine.class\
	antlr/TokenStreamRewriteEngine$$1.class\
	antlr/TokenStreamRewriteEngine$$DeleteOp.class\
	antlr/TokenStreamRewriteEngine$$InsertBeforeOp.class\
	antlr/TokenStreamRewriteEngine$$ReplaceOp.class\
	antlr/TokenStreamRewriteEngine$$RewriteOperation.class\
   antlr/TokenStreamSelector.class\
   antlr/TokenSymbol.class\
   antlr/TokenWithIndex.class\
   antlr/Tool.class\
   antlr/ToolErrorHandler.class\
   antlr/TreeBlockContext.class\
   antlr/TreeElement.class\
   antlr/TreeParser.class\
   antlr/TreeParserSharedInputState.class\
   antlr/TreeSpecifierNode.class\
   antlr/TreeWalkerGrammar.class\
   antlr/Version.class\
   antlr/WildcardElement.class\
   antlr/ZeroOrMoreBlock.class\
   antlr/actions/cpp/ActionLexer.class\
   antlr/actions/cpp/ActionLexerTokenTypes.class\
   antlr/actions/csharp/ActionLexer.class\
   antlr/actions/csharp/ActionLexerTokenTypes.class\
   antlr/actions/java/ActionLexer.class\
   antlr/actions/java/ActionLexerTokenTypes.class\
   antlr/build/ANTLR.class\
   antlr/build/StreamScarfer.class\
   antlr/build/Tool.class\
   antlr/collections/AST.class\
   antlr/collections/ASTEnumeration.class\
   antlr/collections/Enumerator.class\
   antlr/collections/List.class\
   antlr/collections/Stack.class\
   antlr/collections/impl/ASTArray.class\
   antlr/collections/impl/ASTEnumerator.class\
   antlr/collections/impl/BitSet.class\
   antlr/collections/impl/IndexedVector.class\
   antlr/collections/impl/IntRange.class\
   antlr/collections/impl/LLCell.class\
   antlr/collections/impl/LLEnumeration.class\
   antlr/collections/impl/LList.class\
   antlr/collections/impl/Vector.class\
   antlr/collections/impl/VectorEnumeration.class\
   antlr/collections/impl/VectorEnumerator.class\
   antlr/debug/DebuggingCharScanner.class\
   antlr/debug/DebuggingInputBuffer.class\
   antlr/debug/DebuggingParser.class\
   antlr/debug/Event.class\
   antlr/debug/GuessingEvent.class\
   antlr/debug/InputBufferAdapter.class\
   antlr/debug/InputBufferEvent.class\
   antlr/debug/InputBufferEventSupport.class\
   antlr/debug/InputBufferListener.class\
   antlr/debug/InputBufferReporter.class\
   antlr/debug/LLkDebuggingParser.class\
   antlr/debug/ListenerBase.class\
   antlr/debug/MessageAdapter.class\
   antlr/debug/MessageEvent.class\
   antlr/debug/MessageListener.class\
   antlr/debug/NewLineEvent.class\
   antlr/debug/NewLineListener.class\
   antlr/debug/ParseTreeDebugParser.class\
   antlr/debug/ParserAdapter.class\
   antlr/debug/ParserController.class\
   antlr/debug/ParserEventSupport.class\
   antlr/debug/ParserListener.class\
   antlr/debug/ParserMatchAdapter.class\
   antlr/debug/ParserMatchEvent.class\
   antlr/debug/ParserMatchListener.class\
   antlr/debug/ParserReporter.class\
   antlr/debug/ParserTokenAdapter.class\
   antlr/debug/ParserTokenEvent.class\
   antlr/debug/ParserTokenListener.class\
   antlr/debug/SemanticPredicateAdapter.class\
   antlr/debug/SemanticPredicateEvent.class\
   antlr/debug/SemanticPredicateListener.class\
   antlr/debug/SyntacticPredicateAdapter.class\
   antlr/debug/SyntacticPredicateEvent.class\
   antlr/debug/SyntacticPredicateListener.class\
   antlr/debug/TraceAdapter.class\
   antlr/debug/TraceEvent.class\
   antlr/debug/TraceListener.class\
   antlr/debug/Tracer.class\
   antlr/debug/misc/ASTFrame.class\
	antlr/debug/misc/ASTFrame$$1.class \
	antlr/debug/misc/ASTFrame$$MyTreeSelectionListener.class \
   antlr/debug/misc/JTreeASTModel.class\
   antlr/debug/misc/JTreeASTPanel.class\
   antlr/preprocessor/Grammar.class\
   antlr/preprocessor/GrammarFile.class\
   antlr/preprocessor/Hierarchy.class\
   antlr/preprocessor/Option.class\
   antlr/preprocessor/Preprocessor.class\
   antlr/preprocessor/PreprocessorLexer.class\
   antlr/preprocessor/PreprocessorTokenTypes.class\
   antlr/preprocessor/Rule.class\
   antlr/preprocessor/Tool.class
#
# Define what to make.
#

#
#  Include examples?
#
SUBDIRS := antlr lib
ifeq (yes,$(WITH_EXAMPLES))
SUBDIRS += examples
endif
ALL_SUB_DIRS = $(SUBDIRS) $(DOCDIR)

#
# Generate dependencies if they don't exist (mainly for C++)
#
ifneq (.depend,$(wildcard .depend))
CONFIGURATION := depend
endif

#
#  Jars, only make when JAVAC defined
#
JAR_PREBUILT := antlr.jar
ifneq ($(strip $(JAVAC)),)
JAR_TARGETS := $(JAR_PREBUILT)
endif

#
#  Additional "distclean" targets...
#  FIXME: should config.log and config.status be moved to general
#  Rules.make distclean target?
#
DC_TARGETS = config.log config.status scripts/antlr-config scripts/run-antlr scripts/Config.make

#
# Rules start here.
#
.PHONY: all
all: $(CONFIGURATION) subdirs $(JAR_TARGETS) Newversion

#
# Rules to update version file containing the build number.
#
.PHONY: Newversion
Newversion: $(VERSION_FILE);
	@ expr 0`cat $(VERSION_FILE)` + 1 > $(VERSION_FILE)

$(VERSION_FILE): ;
	@ if [ ! -f $(VERSION_FILE) ]; then \
		echo 1 > $(VERSION_FILE); \
	fi

#
# Generic version tracking system ;)
#
antlr/Version.java: $(VERSION_FILE)
	@echo "Rebuilding Version.java"
	@echo "package antlr;"  > $@
	@echo "public class Version {"  >> $@
	@echo "	public static final String version    = \"$(VERSION)\";"  >> $@
	@echo "	public static final String subversion = \"$(SUBVERSION)\";"  >> $@
	@echo "	public static final String patchlevel = \"$(PATCHLEVEL)\";"  >> $@
	@echo "	public static final String datestamp  = \"$(DATE)\";"  >> $@
	@echo "	public static final String project_version = \"$(VERSION).$(SUBVERSION).$(PATCHLEVEL) ($(DATE)-$(shell cat $(VERSION_FILE)))\";"  >> $@
# Or if you don't want the build no in it... use this line..
#	@echo "	public static final String project_version = \"$(VERSION).$(SUBVERSION).$(PATCHLEVEL) ($(DATE))\";"  >> $@
	@echo "}"  >> $@

#
# Rules to make automatic dependencies.
# NOTE: Java dependencies only in this subdir (see below).
.PHONY: depend
depend: java_deps
	@echo =============================
	@echo Making dependencies...
	@echo =============================
	@-$(RM) -f .depend
#
#  Handle java dependencies differently since they are harder to
#  generate. The dependencies are probably a little bit more
#  aggressive than necessary in most cases.
#  NOTE: For now, ./java_deps is generated externally and modified
#  into ./.depend by eliding Version.class and appending a dependency
#  for each inner classes (found in the combined list of .class files
#  by their distinctive embedded $) on its enclosing class .class file.
#  The complexity of the statement to generate the inner class
#  dependencies is due to: 1) make eating newlines in variable
#  assignment; 2) make requiring $$ for $ in both commands and
#  included files (.depend); and 3) /bin/sh's (echo) interpretation
#  of an unescaped $x as the variable x.  Lovely.
#
#  RK: got the impression this stuff is not 100% stable across sh versions
#
	cat java_deps | grep -v ": antlr/Version.class" >> .depend
	for i in $(subst $$,\$$\$$, $(antlr_jar_FILES)) ; do echo $$i ; done \
	  | sed -n  -e 's,^[ 	]*\([^$$]*\)\($$$$.*\)\.class.*$$,\1\2.class : \1.class,p' \
	  >> .depend
# Do subdirs
	@set -e; for i in $(SUBDIRS); do $(MAKE) -C $$i fastdep; done
	@$(TOUCH) .depend		# Record rule execution event.

#
# Specify dependencies for the jar files.
# (required by %.jar implicit rule structure)
#
antlr.jar: $(antlr_jar_FILES)

#
#  Rule to build an ANTLR from a cleaned distribution
#  by first using an alternate ANTLR jar, and second
#  rebuilding using the interim ANTLR. The idea being
#  that this will leave the distribution with an ANTLR
#  jar consistent with the antlr.g, et al., of the
#  distribution. Inspired by GCC, but distinctly less
#  sophisticated. Once this completes, execute "make"
#  to use the new antlr.Tool to build itself for the
#  final time.
#
.PHONY: bootstrap
bootstrap: $(CONFIGURATION)
	$(MAKE) -C antlr bootstrap_g
	$(MAKE) antlr.jar
	$(MAKE) -C antlr clean_g
	$(MAKE) -C antlr
	$(MAKE) antlr.jar
	$(MAKE) -C antlr clean_g

#
#  Subdirs rule for make <no_goal_specified>.
#  FIXME: merge with Rules.make:sub_dirs?
#
.PHONY: subdirs
subdirs:
	@set -e; for i in $(SUBDIRS); do \
		echo ============================= ; \
		echo Making $$i ...                ; \
		echo ============================= ; \
		$(MAKE) -C $$i; \
	done

#
# Rule to remove all objects, cores, ANTLR generated,
# configure generated, etc. This is not a recursive rule
# because distclean removes files unconditionally
# included by subdirectory Makefiles.
#
.PHONY: maintainer-clean
maintainer-clean: distclean
	-$(RM) -f configure $(VERSION_FILE)

#
# Rule to make a tarball exclusive all kinds of fluff
#
.PHONY: tar backup
tar backup:
#	-([ -n "$(DOCDIR)" ] && cd "$(DOCDIR)" && make clean)
	-(cd .. ; \
	$(TAR) cvfz $(TAR_FILE) \
				--exclude *.o \
				--exclude *.lo \
				--exclude *.a \
				--exclude *.la \
				--exclude *.lai \
				--exclude *.so \
				--exclude *.class \
				--exclude .deps \
				--exclude .depend \
				--exclude config.cache \
				--exclude config.status \
				--exclude Config.make \
				--exclude antlr-config \
				--exclude run-antlr \
				--exclude *~ \
				--exclude core \
				--exclude dmalloc.log \
				--exclude .gdb_history \
				--exclude ".nfs*" \
				--exclude "$(TAR_DIR)/gen_doc/html" \
				--exclude "$(TAR_DIR)/gen_doc/html/*" \
				$(TAR_DIR) ; \
   chmod 660 $(TAR_FILE) )

#
# Include basic make rules
#
OVERRULE_INSTALL=yes
include scripts/Rules.make
unexport OVERRULE_INSTALL
#
# Override the default install rule...
#
.PHONY: install
install:
	@test -d $(DESTDIR)$(datadir)/$(versioneddir) || $(MKDIR) -p $(DESTDIR)$(datadir)/$(versioneddir)
	@test -d $(DESTDIR)$(bindir) || $(MKDIR) -p $(DESTDIR)$(bindir)
	@echo "Installing jar files into $(datadir)/$(versioneddir)"
	@for i in $(JAR_PREBUILT); do \
		$(INSTALL) -m 644 $$i $(DESTDIR)$(datadir)/$(versioneddir) ;\
	done
	@echo "Installing scripts/binaries into $(bindir)"
	if [ -f antlr.bin ]; then \
		$(INSTALL) -m 755 antlr.bin $(DESTDIR)$(bindir)/antlr ;\
		$(INSTALL) -m 755 scripts/run-antlr $(DESTDIR)$(bindir)/antlr-java ;\
	else \
		$(INSTALL) -m 755 scripts/run-antlr $(DESTDIR)$(bindir)/antlr ;\
	fi
	@$(INSTALL) -m 755 scripts/antlr-config $(DESTDIR)$(bindir)
ifdef ALL_SUB_DIRS
	@set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i install; done
endif
