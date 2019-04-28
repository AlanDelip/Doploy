FROM python:3.6

WORKDIR /home/app

RUN pip install flask pymongo

CMD python app/app.py
