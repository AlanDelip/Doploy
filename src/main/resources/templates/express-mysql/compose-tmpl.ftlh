version: "3.7"

services:
    express:
        restart: on-failure
        build: .
        ports:
            - ${port}:4000
        volumes:
            - ./:/home/app
<#if dbname??>
        depends_on:
            - ${dbhost}

    ${dbhost}:
        image: mysql:5.7
        restart: on-failure
        environment:
            MYSQL_USER: ${dbuser}
            MYSQL_PASSWORD: ${dbpass}
            MYSQL_DATABASE: ${dbname}
            MYSQL_ROOT_PASSWORD: ${dbrootpass}
        volumes:
            - type: volume
              source: mysql-data
              target: /var/lib/mysql
            - type: bind
              source: ./${dbinitentry}
              target: /docker-entrypoint-initdb.d/

volumes:
    mysql-data:
</#if>