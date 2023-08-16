#!/bin/bash
mvn clean verify -Dmaven.test.failure.ignore=true

export JAVA_HOME=/Users/honggao.chen/Downloads/jdk-17.0.8.jdk/Contents/Home
export JAVA_HOME=/Users/honggao.chen/Downloads/jdk-11.0.16.1.jdk/Contents/Home

mvn sonar:sonar  \
    -Dsonar.token=bf8d6b8ec446e7769fab78000623c9a84b9e9059 \
    -Dsonar.host.url=https://sonarcloud.io \
    -Dsonar.organization=chenhonggao \
    -Dsonar.projectKey=chenhonggao_sonar_test \
    -Dsonar.branch.name=sonar