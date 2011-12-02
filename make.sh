#!/usr/local/bin/bash

if [ $# -ne 1 ]
then
	echo "Nope"
	exit 0
fi

if [ "$1" == "compile" ]
then
	javac -d . *.java
fi

if [ "$1" == "run" ]
then
	java com/purdue/LawsonNavigator/Server
fi

if [ "$1" == "clean" ]
then
	cd com/purdue/LawsonNavigator/
	rm *.class
	cd ~/gitrepos/LawsonNavigatorServer
fi