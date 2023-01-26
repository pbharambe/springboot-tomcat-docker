FROM tomcat:8.5-jdk8-corretto
#RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./target/SimpleApplication.war /usr/local/tomcat/webapps/SimpleApplication.war
#COPY server.xml /usr/local/tomcat/conf/server.xml
EXPOSE 8080
CMD ["catalina.sh", "run"]
