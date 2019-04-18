version: "3"
services:
    web:
        build: .
        ports:
            - "${port}:5000"
    volumes:
        - ./${workdir}/:/home/app/
    depends_on:
        - localhost

    localhost:
        image: postgres:10
        env_file: env
        expose:
            - 5432