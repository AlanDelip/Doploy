version: "3.7"

services:
    web:
        build: .
        ports:
            - ${port}:4000
        volumes:
            - ./:/home/app
<#if dbname??>
        depends_on:
            - ${dbhost}

    ${dbhost}:
        image: postgres:11
        restart: on-failure
        environment:
            POSTGRES_DB: ${dbname}
            POSTGRES_USER: ${dbuser}
            POSTGRES_PASSWORD: ${dbpass}
        volumes:
            - type: volume
              source: pg-data
              target: /var/lib/postgresql/data
            - type: bind
              source: ./${dbinitentry}
              target: /docker-entrypoint-initdb.d/
    <#if dbport??>
        ports:
            - ${dbport}:5432
    </#if>

volumes:
    pg-data:
</#if>
