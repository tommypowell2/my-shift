FROM ubuntu
MAINTAINER Tommy Powell
WORKDIR /myshift
#ADD jdk-8u112-linux-x64.tar.gz .
RUN apt-get update \
    && apt-get install wget -y

RUN wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u112-b15/jdk-8u112-linux-x64.tar.gz \
&& tar -xvf jdk-8u112-linux-x64.tar.gz \
&& mkdir -p /usr/lib/jvm \
&& mv ./jdk1.8.0_112 /usr/lib/jvm/jdk1.8.0 \
&& update-alternatives --install "/usr/bin/java" "java" "/usr/lib/jvm/jdk1.8.0/bin/java" 1702 \
&& update-alternatives --install "/usr/bin/javac" "javac" "/usr/lib/jvm/jdk1.8.0/bin/javac" 1 \
&& update-alternatives --install "/usr/bin/javaws" "javaws" "/usr/lib/jvm/jdk1.8.0/bin/javaws" 1 \
&& chmod a+x /usr/bin/java \
&& chmod a+x /usr/bin/javac \
&& chmod a+x /usr/bin/javaws \
&& rm -rf jdk-8u112-linux-x64.tar.gz

#You will need to copy the jar to the same directory as this "Dockerfile" in order for the ADD command to work
ADD my-shift-1.0.0.jar .

CMD java -jar my-shift-1.0.0.jar .

#Example command to build this image
#docker build -t myshift:beta .

# Run the following after the database is up. You w
#docker run --link myshiftDB:postgres --name myshifyApp -p 8080:8080  myshift:beta
