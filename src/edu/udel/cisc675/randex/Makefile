# Makefile for Randex.  Type "make" to compile all Java classes.
# Type "make test1" to run a test involving exam1.tex.  Ditto
# for test2, test3.

SRC = Main.java Randex.java Input.java ProblemFinder.java FindAnswers.java \
  RandomizeProblems.java RandomizeAnswers.java StringUtils.java PrintUtil.java Output.java
CLASSES = $(subst .java,.class,$(SRC))
CP = ../../../..

all: $(CLASSES)

$(CLASSES): $(SRC)
	javac $(SRC)

test1: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam1.tex 1

test2: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam2.tex 2

test3: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam3.tex 3

test4: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam4.tex 4

test5: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam5.tex 5

test6: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Main exam6.tex 6

clean:
	rm -f *.class *~

.PHONY: clean all
