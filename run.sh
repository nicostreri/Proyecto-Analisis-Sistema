#!/bin/bash

echo "*******************  BUILDING MODULE  *****************************************"
mvn clean install

echo "*******************  COLLECTING DEPENDENCIES  *********************************"
mvn dependency:copy-dependencies
export CLASSPATH=""
for file in `ls target/dependency`; do  export CLASSPATH=$CLASSPATH:target/dependency/$file; done
export CLASSPATH=$CLASSPATH:target/classes

echo "*******************  EXECUTING PROGRAM******************************************"
java -cp $CLASSPATH -Dactivejdbc.log prode.App

