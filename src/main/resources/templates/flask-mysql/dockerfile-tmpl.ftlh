FROM python:3.7

EXPOSE 5000

WORKDIR /home/app

RUN pip install flask mysql-connector ${dependencies}

CMD python ${entry}