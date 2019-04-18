#!/bin/sh
# kill running application
pid=`ps -def | grep '*SpringBootTmpl-LATEST.jar.*' | grep -v grep | awk '{print $2}'`

if [ -z "$pid" ]
then
     echo "No application is running."
else
     kill $pid
fi

# maven clean & package
mvn clean
mvn package

# deploy
# waiting for the database connection
while ! nc -z database 3306;
    do
        echo 'waiting for the database connection';
        sleep 1;
    done;
echo Database Connected!;

# run the built springboot project
java -jar target/SpringBootTmpl-LATEST.jar