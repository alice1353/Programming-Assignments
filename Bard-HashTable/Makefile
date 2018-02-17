# Makefile
# Purpose: A Makefile with macros
# Creates Bard.jar file
# 12/05/2017

JAVASRC    = Bard.java WordObj.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = Bard
CLASSES    = Bard.class WordObj.class
JARFILE    = Bard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)