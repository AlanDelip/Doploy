# Basic flask container
FROM fanoftal2/flask-crud-base:1

WORKDIR /home/app/

EXPOSE ${port}

CMD ["python3", "${entry}"]