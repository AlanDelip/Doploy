version: "3.7"

services:
    web:
        build: .
        ports:
            - ${port}:5000
        volumes:
            - ./${workdir}:/home/app
<#if dbname??>
    depends_on:
        - localhost

    localhost:
        image: postgres:10
        env_file: env
        volumes:
            - pg-data:/var/lib/postgresql/data
    <#if dbport??>
        ports:
            - ${dbport}:5432
    </#if>

volumes:
    pg-data:
</#if>