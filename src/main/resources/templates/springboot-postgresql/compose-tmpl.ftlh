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
        image: postgres:10
        environment:
            POSTGRES_DB: ${dbname}
            POSTGRES_USER: ${dbuser}
            POSTGRES_PASSWORD: ${dbpass}
        volumes:
            - pg-data:/var/lib/postgresql/data
        restart: on-failure
        volumes:
            - mysql-data:/var/lib/mysql
        <#if dbport??>
        ports:
            - ${dbport}:5432
        </#if>
    </#if>

volumes:
<#if dbname??>
    pg-data:
</#if>
    maven-repo: