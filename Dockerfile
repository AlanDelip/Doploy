FROM python:3.6

EXPOSE 5000

WORKDIR /app

RUN pip install Flask mysql-connector

CMD python app/app.py
