#!/bin/bash

set -e

mvn spring-boot:run &
mvn_pid=$!

# wait for server to start
# mostly copied from so
attempt=0
max_attempts=10
until $(curl --output /dev/null --silent --head --fail http://172.17.0.1:8080); do
    if [ ${attempt} -eq ${max_attempts} ];then
      echo "Server did not come up in time"
      exit 1
    fi

    echo "Waiting for server to come up... ($attempt out of $max_attempts)"

    attempt=$(($attempt+1))
    sleep 3
done

node js/Tests.mjs

kill $mvn_pid
