version: '3.7'

services:
    springboot:
        build: .
        ports:
            - ${port}:8080
        restart: on-failure
        volumes:
            - type: volume
              source: maven-repo
              target: /root/.m2
            - type: bind
              source: ./
              target: /home/app
<#if dbname??>
        depends_on:
            - ${dbhost}

    ${dbhost}:
        image: mongo
        restart: on-failure
        environment:
            MONGO_INITDB_ROOT_USERNAME: ${dbuser}
            MONGO_INITDB_ROOT_PASSWORD: ${dbpass}
        volumes:
            - mongo-data:/data/db
        <#if dbport??>
        ports:
            - ${dbport}:27017
        </#if>
</#if>

volumes:
<#if dbname??>
    mongo-data:
</#if>
    maven-repo: