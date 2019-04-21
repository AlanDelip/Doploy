# Basic flask container
FROM fanoftal2/flask-crud-base:1

WORKDIR /home/app/

EXPOSE ${port}

<#if dependencies??>
RUN pip install --upgrade pip &&\
    pip install --no-cache-dir ${dependencies}
</#if>
CMD ["python3", "${entry}"]