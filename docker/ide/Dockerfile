FROM openjdk:8

WORKDIR /opt/idea/
VOLUME /tmp/.X11-unix
# COPY ideaIC-2017.1.2.tar.gz .
COPY start-intellij.sh .

# Run a container with the following params
# docker run -it -e DISPLAY -v /opt/dockermount/:/opt/dockermount/ -v /var/run/docker.sock:/var/run/docker.sock --net=host --name <container-name> <image-name>
# This assumes you have a file with the results of xauth list written to a file called /opt/dockermount/xadd on your host
# The start-intellij.sh script reads from that file to gain access to the X11 server :)

RUN apt-get update && \
    su -c "apt-get -y install wget xauth git \
           apt-transport-https \
           ca-certificates \
           curl \
           gnupg2 \
           software-properties-common" && \
   curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
   add-apt-repository  "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" && \
   apt-get -y update && \
   apt-get -y install docker-ce && \ 
   wget https://download-cf.jetbrains.com/idea/ideaIC-2017.1.2.tar.gz && \
   tar -xvf ideaIC-2017.1.2.tar.gz && \
   rm -rf ideaIC-2017.1.2.tar.gz && \
   curl -sL https://deb.nodesource.com/setup_8.x | bash - && \
   apt-get install -y nodejs
   

CMD ./start-intellij.sh
