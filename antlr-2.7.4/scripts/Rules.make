###############################################################################
# $Id: Rules.make,v 1.1.1.1 2004-12-13 19:09:15 deronj Exp $
###############################################################################
#
# Rules.make :
#  General make rules, supporting
#   1 -compilation of .cpp to .o, .cpp to .s and .s to .o
#   2 -combining of multiple .o files into one single .o file
#   3 -creation of .a archives from multiple .o files
#   5 -recursive subdirectories
#   6 -recursive dependencies
#   7 -recursive (dist)clean
#   8 -creation of binary executables
#   9 -creation of TCL loadable .so files
#  10 -doxygen generated docs
#

.EXPORT_ALL_VARIABLES:

#
# Depending on the definition of these variables in submakefiles different
# targets get build.
# NOTE: These should not be exported so we unexport them right here.
#
unexport SUBDIRS			# specifying all subdirs from $(TOPDIR)
unexport SUB_DIRS       # specifying all subdirs to make
unexport ALL_SUB_DIRS   # specifying all subdirs to process (dep/clean)

unexport O_TARGET			# name of combined .o file to generate (2)
unexport O_OBJS			# name of .o files to combine into $(O_TARGET) (2)

unexport L_TARGET			# name of .a archive to generate (3)
unexport L_OBJS			# name of .o files to place in $(L_TARGET) (3)

unexport B_NAME			# name of binary to generate (8)
unexport B_OBJS			# name of .o files to combine into $(B_NAME) (8)

unexport TCL_TARGET		# name of .so file to create (9)
unexport TCL_OBJS			# name of .o files to place in $(TCL_TARGET) (9)
unexport TCL_LIBS			# libs needed to build .so file (9)

unexport DOXY_TARGET		# name of a doxygen config file to build docs from (10)
unexport DOXY_GENDIR    # name of dir doxygen generates docs into

unexport JAR_TARGETS		# jar files..

unexport G_FILES        # antlr .g files
unexport G_TAG_FILES		# empty file to record compiling .g files
unexport G_TARGETS		# per-Makefile list of ANTLR generated files

unexport C_TARGETS		# name of additional targets to "clean"
unexport DC_TARGETS		# name of additional targets to "distclean"

#
# Implicit rules
#
%.s:	%.cpp
	$(CXX) $(CXXFLAGS) $(EXTRA_CXXFLAGS) -S $< -o $@
#
# These rules support buiding the .o files into a obj_dir different than the
# current dir. This keeps the source dirs nice and clean..
# FIXME: VPATH?
#
$(obj_dir)%.o:	%.cpp
ifdef VERBOSE
	$(CXX) -c -o $@ $< $(CXXFLAGS) $(SHARED_FLAGS) $(EXTRA_CXXFLAGS)
else
	@echo "Building $@"
	@$(CXX) -c -o $@ $< $(CXXFLAGS) $(SHARED_FLAGS) $(EXTRA_CXXFLAGS)
endif
$(obj_dir)%.o: %.c
	@echo "Building $@"
	@$(CC) -c -o $@ $< $(CFLAGS) $(SHARED_FLAGS) $(EXTRA_CFLAGS)
$(obj_dir)%.o: %.s
	@echo "Building $@"
	@$(AS) $(ASFLAGS) -o $@ $<

#
# How to make .x.g files from .g files. (ANTLR)
# A .x.g file is an empty target to record running ANTLR on x.g
# Customize flags per file 'x.g' by setting x_FLAGS
# The chmod is dirty but it makes life a lot easier
#
.%.g: %.g ;
	@ -$(CHMOD) -f u+w $($(addsuffix _FILES, $(subst .,_,$^)))
	$(ANTLR) $(ANTLR_FLAGS) $($*_FLAGS) $^
	@ $(TOUCH) $@

ifdef G_FILES
#  G_TAG_FILES is used as a build target set for the
#  above implicit .g rule.
G_TAG_FILES := $(addprefix .,$(G_FILES))
#  G_TARGETS is the set of generated files and
#  depends on x_g_FILES being defined for each x.g
G_TARGETS := $(foreach target,$(addsuffix _FILES, $(subst .,_,$(G_FILES))),$($(target)))
endif

#
# How to build class files
#
ifdef obj_dir
 # FIXME: obj_dir stuff not tested
 obj_dir_arg	= -d $(call fix_path,$(obj_dir))
 javac_paths	= -sourcepath $(call fix_path,$(TOPDIR)) \
                 -classpath $(call fix_path,$(obj_dir))
else
# Take along existing CLASSPATH definition. Necessary for jikes.
 ifdef CLASSPATH
  ifneq ($(CLASSPATH),)
   javac_paths= -classpath $(call fix_path,$(TOPDIR)$(PATH_SEPARATOR)$(CLASSPATH))
  else
   javac_paths	= -classpath $(call fix_path,$(TOPDIR))
  endif
 endif
endif

$(obj_dir)%.class: %.java
ifdef VERBOSE
	$(JAVAC) $(JAVAC_FLAGS) $(javac_paths) $(obj_dir_arg) $<
else
	@ echo "Building $<"
	@ $(JAVAC) $(JAVAC_FLAGS) $(javac_paths) $(obj_dir_arg) $<
endif
unexport obj_dir_arg javac_paths

#
#  How to build a Java jar file.
#
#  Note: The jar contents are taken from the rule dependency
#  list; thus the user must explicitly define dependencies per
#  jar target.  E.g.
#     x.jar : $(x_jar_FILES)
#  Make performs variable expansion before implicit rule search,
#  hence, the desired implicit rule dependency
#	$($(subst .,_,$%)_jar_FILES)
#  is an undefined variable, resulting in an empty dependency
#  list.
#
%.jar:
	@ echo "==========================================="
	@ echo "Making $(@)..."
	@ rm -f $@
	@ $(JAR) cf $@ $(subst $$,\$$,$^)
	@ echo "==========================================="

#
# Get things started:
# 1. Build ANTLR generated files, subdirectories.
# 2. Remaining TARGETS
#
first_rule: $(G_TAG_FILES) sub_dirs
	$(MAKE) all_targets
#
# Build default targets
#
all_targets: $(O_TARGET) $(L_TARGET) $(B_NAME) $(TCL_TARGET) $(JAR_TARGETS)

#
# Compile a set of .o files into one .o file
#
ifdef O_TARGET
$(O_TARGET): $(O_OBJS)
	-$(RM) -f $@
ifneq "$(strip $(O_OBJS))" ""
	$(LD) $(EXTRA_LDFLAGS) -r -o $@ $(O_OBJS)
else
	$(AR) rus $@
endif
endif

#
# Compile a set of .o files into one .a file
#

ifdef L_TARGET
$(L_TARGET): $(L_OBJS)
	-$(RM) -f $@
	$(AR) $(EXTRA_ARFLAGS) $(ARFLAGS) $@ $(L_OBJS)
	$(RANLIB) $@
endif

ifeq ($(ANTLR_WIN32),"yes")
ifdef DLL_TARGET
$(DLL_TARGET): $(DLL_OBJS)
	-$(RM) -f $@
	$(CXX) -o $@ -Wl,-mdll $(L_OBJS)
endif
endif

ifdef DOXY_TARGET
gen_doc: $(DOXY_TARGET) ;
ifdef DOXY_GENDIR
ifneq ($(DOXY_GENDIR),)
	-$(RM) -f $(DOXY_GENDIR)/*
endif
endif
ifneq ($(DOXYGEN),"")
	$(DOXYGEN) -f $(DOXY_TARGET)
else
	echo "Doxygen not installed skipping $(DOXY_TARGET)"
endif
endif

#
# Compile .cpp files into a .so file loadable by tcl and generate an index
# file
#
ifdef TCL_TARGET
$(TCL_TARGET): $(TCL_OBJS)
	-$(RM) -f $@
	$(CXX) $(EXTRA_LDFLAGS) -shared -o $@ $(TCL_OBJS) $(TCL_LIBS)
	echo "pkg_mkIndex . $(TCL_TARGET)" | $(TCL)
endif

# Rule to make subdirs
#
.PHONY: sub_dirs
sub_dirs:
ifdef SUB_DIRS
ifneq ($(strip $(SUB_DIRS)),)
	@ set -e ; for i in $(SUB_DIRS); do\
	echo ">>>>>>>>>>>>>>>>>>> $$i";\
	$(MAKE) -C $$i;\
	echo "<<<<<<<<<<<<<<<<<<< $$i";\
	done
endif
endif

#
# Rule to make binaries
#

ifdef B_NAME
$(B_NAME): $(B_OBJS)
	-$(RM) -f $@
	$(CXX) -o $@ $(EXTRA_LDFLAGS) $(B_OBJS) $(EXTRA_LIBS)
endif

#
# Include dependency files if they exist
#

#
# some wicked makefile magic.. used to quote a path name for for instance a
# sed expression. This is used to work around some dependency generation
# errors in gcc.
# FIXME: this probably should be configured. gcc 3.0 does this correct.
#
quote = $(call quote2,$(call quote1,$(1)))
quote1 = $(subst .,\\.,$(1))
quote2 = $(subst /,\\/,$(1))

.PHONY: dep fastdep
dep fastdep:
ifdef obj_dir
	-( touch .depend ; \
	    $(CXX) -MM $(CXXFLAGS) $(CFLAGS) $(SHARED_FLAGS) \
		 	$(EXTRA_CXXFLAGS) $(EXTRA_CFLAGS) *.c *.cpp | \
				sed "s/\([a-zA-Z_\-]*\.o\)/$(call quote,$(obj_dir))\1/g" > .depend ; \
		 $(RM) -f .depend.bak \
	  )
else
	@-( touch .depend ; \
	    $(CXX) -MM $(CXXFLAGS) $(CFLAGS) $(SHARED_FLAGS) \
		 	$(EXTRA_CXXFLAGS) $(EXTRA_CFLAGS) *.c *.cpp > .depend 2> /dev/null ; \
		 $(RM) -f .depend.bak \
	  )
endif
ifdef ALL_SUB_DIRS
	@set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i fastdep; done
endif

ifeq (.depend,$(wildcard .depend))
include .depend
endif

#
#  Rule to bootstrap from external ANTLR jar.
#
.PHONY: bootstrap_g
bootstrap_g: ANTLR := $(ANTLR_BOOTSTRAP)
bootstrap_g: ANTLR_FLAGS := $(ANTLR_BOOTSTRAP_FLAGS)
bootstrap_g: $(G_TAG_FILES)
ifdef ALL_SUB_DIRS
	@set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i bootstrap_g; done
endif

#
#  Rule to clean ANTLR generated files (corresponding
#  to bootstrap_g targets).
#
.PHONY: clean_g
clean_g:
ifdef ALL_SUB_DIRS
	@set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i clean_g; done
endif
	-$(RM) -f $(G_TAG_FILES) $(G_TARGETS)

#
# Rule to remove all objects, cores, etc.; leaving
# ANTLR generated and config files.
#
.PHONY: mostlyclean
mostlyclean:
ifdef obj_dir
ifneq ($(obj_dir),)
	-$(RM) -f $(obj_dir)/*
endif
endif
ifdef DOXY_GENDIR
ifneq ($(DOXY_GENDIR),)
	-$(RM) -f $(DOXY_GENDIR)/*
endif
endif
ifdef ALL_SUB_DIRS
	set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i mostlyclean; done
endif
	-$(RM) -f *.o *.class *.a *.so core *.s $(B_NAME) $(C_TARGETS) $(JAR_TARGETS)

#
# Rule to remove all objects, cores, ANTLR generated, etc.;
# leaving configure generated files.
#
.PHONY: clean
clean: mostlyclean clean_g
ifdef ALL_SUB_DIRS
	set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i clean; done
endif

#
# Rule to remove all objects, cores, ANTLR generated,
# configure generated, etc.; leaving files requiring
# additional programs to generate (e.g., autoconf).
#
# FIXME: can not be called more than once successively because
# FIXME: it removes files unconditionally included by subdirectory
# FIXME: Makefiles (e.g., Config.make).
#
.PHONY: distclean
distclean: clean
ifdef ALL_SUB_DIRS
	set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i distclean; done
endif
	-$(RM) -f .depend $(DC_TARGETS)

#
#  Install rule
#
ifndef OVERRULE_INSTALL
.PHONY: install
install:
ifdef B_NAME
	@echo "Installing $(B_NAME) into $(bindir)"
	@test -d $(DESTDIR)$(bindir) || $(MKDIR) -p $(DESTDIR)$(bindir)
	@$(INSTALL) -m 755 $(B_NAME) $(DESTDIR)$(bindir)
endif
ifdef L_TARGET
	@echo "Installing $(L_TARGET) into $(libdir)"
	@test -d $(DESTDIR)$(libdir) || $(MKDIR) -p $(DESTDIR)$(libdir)
	@$(INSTALL) -m 644 $(L_TARGET) $(DESTDIR)$(libdir)
endif
ifdef JAR_TARGETS
	@test -d $(DESTDIR)$(datadir)/$(versioneddir) || $(MKDIR) -p $(DESTDIR)$(datadir)/$(versioneddir)
	@for i in $(JAR_TARGETS); do \
		echo "Installing $i into $(datadir)/$(versioneddir)" \
		$(INSTALL) -m 644 $$i $(DESTDIR)$(datadir)/$(versioneddir) ;\
	done
endif
ifdef INSTALL_TARGETS
	@test -d $(DESTDIR)$(INSTALL_DIR) || $(MKDIR) -p $(DESTDIR)$(INSTALL_DIR)
	@for i in $(INSTALL_TARGETS); do \
		echo "Installing $$i into $(INSTALL_DIR)" ; \
		$(INSTALL) -m $(INSTALL_MODE) $$i $(DESTDIR)$(INSTALL_DIR) > /dev/null ;\
	done
endif
ifdef ALL_SUB_DIRS
	@set -e; for i in $(ALL_SUB_DIRS); do $(MAKE) -C $$i install; done
endif
endif
