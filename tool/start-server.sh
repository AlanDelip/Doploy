#!/bin/sh
# default settings
profile='dev'

# extract param from instructions
while getopts "p:" OPT
do
    case $OPT in
        p)
            profile=$OPTARG
            ;;
    esac
done

# maven clean & package
mvn clean
mvn package

# deploy
java -jar -Dspring.profiles.active=$profile target/Doploy-LATEST.jar > run.log &