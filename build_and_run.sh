#! /bin/bash

gradle clean build

java -jar build/libs/demo-0.0.1-SNAPSHOT.jar run=three a=b
