FROM node:10

WORKDIR /home/app

EXPOSE 4000

CMD ["sh","-c","npm install && node ${entry}"]