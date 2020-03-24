# This file basically only rebuilds the ANTLR generated stuff.
G_FILES := antlr.g tokdef.g

SUB_DIRS := preprocessor actions collections debug
ALL_SUB_DIRS := $(SUB_DIRS)

# What's generated from antlr.g
antlr_g_FILES := \
   ANTLRParser.java \
   ANTLRTokenTypes.java \
   ANTLRTokenTypes.txt \
   ANTLRLexer.java

# What's generated from tokdef.g
tokdef_g_FILES := \
   ANTLRTokdefParser.java \
   ANTLRTokdefLexer.java \
   ANTLRTokdefParserTokenTypes.java \
   ANTLRTokdefParserTokenTypes.txt

ifneq ($(strip $(GCJ)),)
B_NAME := ../antlr.bin
endif

#
# RK: the gcj stuff is a bit hacked inhere for now. Did not see a really nice
# way to incorporate it in the java build. Maybe I'll get inspiration
# lateron.
#
GCJ_JAVA_FILES := \
	$(wildcard *.java) \
	$(wildcard collections/*.java) \
	$(wildcard collections/impl/*.java) \
	$(wildcard preprocessor/*.java) \
	$(wildcard actions/*/*.java)

GCJ_OBJS := $(GCJ_JAVA_FILES:.java=.o)

ifndef TOPDIR
	TOPDIR  = $(shell pwd)/..
endif

# Leave these above any other rules...
include $(TOPDIR)/scripts/Config.make
include $(TOPDIR)/scripts/Rules.make

#
# Generic rule to build a native object from a java file with gcj
#
%.o:	%.java
	$(GCJ) -c -o $@ $< $(GCJFLAGS) $(EXTRA_GCJFLAGS)

#
# Calc deps with gcj
#
ifneq ($(strip $(GCJ)),)
.PHONY: dep fastdep
dep fastdep : ;
	@-$(RM) -f .depend
	$(GCJ) $(GCJFLAGS)  $(EXTRA_GCJFLAGS) -MM $(GCJ_JAVA_FILES) >> .depend
	@-$(RM) -f .depend.bak
	@$(TOUCH) .depend
endif

#
# Rule to build a native binary from the java files.
#
$(B_NAME) gcj: $(GCJ_OBJS) ;
	$(GCJ) $(GCJFLAGS) $(EXTRA_GCJFLAGS) -o $(B_NAME) --main=antlr.Tool $(GCJ_OBJS)

# Check out all the generated stuff here..
.PHONY: p4_genfiles
p4_genfiles: ;
	p4 edit $(antlr_g_FILES) $(tokdef_g_FILES)

.PHONY: install
install: ;
