#! /bin/bash

gradle clean build

java -jar build/libs/demo-0.0.1-SNAPSHOT.jar inputFile=/home/vishesh/Desktop/code/crud-application-vishesh/file.csv
