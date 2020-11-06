FROM ubuntu-jdk

MAINTAINER Edwin Andrew "edwinandrew96@gmail.com"

ENV version =docker
ENV dbuser
ENV dbpass
ENV jdbcurl

WORKDIR /usr/local/bin

ADD target/pma-app.jar . 

ENTRYPOINT ["java","-jar","pma-app.jar"]

