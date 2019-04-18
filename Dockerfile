FROM node:7

WORKDIR /app

COPY package.json /home/app

RUN npm install

COPY . /home/app

EXPOSE 4000

CMD node server.js

