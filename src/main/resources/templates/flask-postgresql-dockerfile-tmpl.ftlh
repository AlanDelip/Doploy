# Basic flask container
FROM fanoftal2/flask-crud-base:1

ADD ./${workdir} /home/app/
WORKDIR /home/app/

EXPOSE ${port}

ENTRYPOINT ["python3", "${entry}"]