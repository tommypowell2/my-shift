FROM postgres:9.4
MAINTAINER Tommy Powell

#ENV POSTGRES_USER docker
#ENV POSTGRES_PASSWORD docker
ENV POSTGRES_DB myshift
ADD init.sql /docker-entrypoint-initdb.d
CMD ["postgres"]

#Example command to build this image
#docker build -t myshift-db:1.0 .

#Example command to create a container from this image
#docker run -p 5432:5432 --name myshiftDB myshift-db:1.0
