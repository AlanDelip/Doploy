FROM python:3.7

WORKDIR /home/app

RUN pip install flask pymongo ${dependencies}

CMD python ${entry}