#!/bin/bash
CP=resources:build/classes
MAIN_CLASS=home.vitaly.transaction.analyzer.RunAnalyzer
JVM_PARAMS=-Xmx800m
for jar in lib/*.jar extlib/*.jar
do
	CP=$CP:${jar}
done
java $JVM_PARAMS -classpath $CP $MAIN_CLASS

