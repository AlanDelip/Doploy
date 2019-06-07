FROM python:3.7

WORKDIR /home/app

RUN pip install flask pymongo

CMD python app/app.py
