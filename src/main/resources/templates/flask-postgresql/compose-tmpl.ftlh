version: "3.7"

services:
    web:
        build: .
        ports:
            - ${port}:5000
        volumes:
            - ./:/home/app
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
    <#if dbport??>
        ports:
            - ${dbport}:5432
    </#if>

volumes:
    pg-data:
</#if>