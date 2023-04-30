#!/usr/bin/env bash
if [ ! -e node_modules ]
then
  echo "No node_modules/ present. Maybe you need to run npm install?"
  read -p "Press enter to ignore"
fi

NODE_ENV=development npx babel --watch js --out-dir static/js &
npx_pid=$!
mvn spring-boot:run &
mvn_pid=$!

trap "kill ${npx_pid} ${mvn_pid}; exit 0" INT

wait
