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
    image: mysql
    environment:
      MYSQL_DATABASE: ${dbname}
  <#if dbuser??>
      MYSQL_USER: ${dbuser}
      MYSQL_PASSWORD: ${dbpass}
  </#if>
      MYSQL_ROOT_PASSWORD: ${dbrootpass}
    restart: on-failure
    volumes:
      - mysql-data:/var/lib/mysql
  <#if dbport??>
    ports:
      - ${dbport}:3306
  </#if>
</#if>

volumes:
<#if dbname??>
  mysql-data:
</#if>
  maven-repo: