# Dockerfile's configuration to deploy war file in Tomcat docker image

---
1. Defile web application as root

    Copy war file as ROOT.war, which will treat as root application for Tomcat image.
    ```
    FROM tomcat:8.5-jdk8-corretto
    COPY ./target/SimpleApplication.war /usr/local/tomcat/webapps/ROOT.war
    EXPOSE 8080
    CMD ["catalina.sh", "run"]
   ```
   With is configuration there will be any application context root will be used. Application directly access using http://localhost:8080 URL.


2. Using web application war file as is

   Copy war file as is into Tomcat image.
    ```
    FROM tomcat:8.5-jdk8-corretto
    COPY ./target/SimpleApplication.war /usr/local/tomcat/webapps/SimpleApplication.war
    COPY server.xml /usr/local/tomcat/conf/server.xml
    EXPOSE 8080
    CMD ["catalina.sh", "run"]
   ```
   with this configuration we can use application context root by providing custome ``server.xml``.
