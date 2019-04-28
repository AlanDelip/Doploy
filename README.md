# Doploy
Doploy is a Docker-based configuration tool that helps 
deploy the backend of a web application in a faster and simpler way.

## docker-flask-mysql

This is a simple demo for how to connect to a Mongodb database from a python flask application. To run this on your computer you must first install [docker](https://docs.docker.com/engine/installation/).

## Running

You should also have [docker](https://docs.docker.com/install/). If you're on linux, you probably also want docker-compose. Last I checked (over a year ago) it did not come with docker by default. For Mac and Windows you get it with the default installation.

Once you have all of that, you should be good. No need to install [Postgres](https://www.postgresql.org/) or even Python.

```
docker-compose up --build -d   # Run the container.

docker-compose down   # Stop and remove everything.

# Add your code to the /app/ directory.
# You have to do a fig up/down after each change.
```

The site will be available to you at `localhost:5000`.

## How it works

There are two Dockerfiles. One is the base Dockerfile that has Python and Flask. I've separated that one out so you don't have to keep downloading Python and Flask over and over again. The second one is the application Dockerfile. This is the one that will get updated frequently as you make changes to your Flask app. I found this to be a faster, more convenient iteration cycle for when I was building it.

The `docker-compose.yml` file tells Docker that you need your Flask container and a Postgres container.
