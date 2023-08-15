#!/bin/bash

export JAVA_HOME=/Users/honggao.chen/Downloads/jdk-17.0.8.jdk/Contents/Home

mvn clean verify sonar:sonar -Pcoverage \
    -Dsonar.token=bf8d6b8ec446e7769fab78000623c9a84b9e9059 \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=chenhonggao \
    -Dsonar.projectKey=chenhonggao_sonar_test \
    -Dsonar.branch.name=sonar



#    -Dsonar.exclusions=pom.xml,src/main/java/cn/simple/WorldUtils.java \
#mvn clean verify sonar:sonar -Pcoverage \
