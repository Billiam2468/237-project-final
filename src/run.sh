#!/bin/bash
#This script should automatically compile and run th files

javac -cp cse237/gson-2.8.6.jar cse237/*.java cse237/Run.java
java -cp .:./cse237/gson-2.8.6.jar cse237.Run
