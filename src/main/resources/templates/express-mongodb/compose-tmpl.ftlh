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
    image: mongo
    restart: on-failure
    volumes:
      - mongo-data:/data/db
  <#if dbport??>
    ports:
      - ${dbport}:27017
  </#if>

volumes:
  mongo-data:
</#if>