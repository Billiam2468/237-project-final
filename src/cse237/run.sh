#!/bin/bash

#This script should automatically compile and run the files

javac -cp gson-2.8.6.jar FileParser.java
java -cp gson-2.8.6.jar FileParser.java
