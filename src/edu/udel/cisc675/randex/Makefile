# Makefile for Randex.  Type "make" to compile all Java classes.
# Type "make test1" to run a test involving exam1.tex.  Ditto
# for test2, test3.

SRC = Randex.java Input.java FindProblems.java FindAnswers.java \
  RandomizeProblems.java RandomizeAnswers.java Output.java
CLASSES = $(subst .java,.class,$(SRC))
CP = ../../../..

all: $(CLASSES)

$(CLASSES): $(SRC)
	javac $(SRC)

test1: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Randex exam1.tex 1

test2: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Randex exam2.tex 2

test3: $(CLASSES)
	java -cp $(CP) edu.udel.cisc675.randex.Randex exam3.tex 3


clean:
	rm -f *.class *~

.PHONY: clean all
