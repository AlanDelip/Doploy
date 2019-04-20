FROM node:10

COPY ./ /home/app

WORKDIR /home/app

RUN npm install

EXPOSE 4000

CMD node server.js