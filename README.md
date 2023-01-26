# How to run SpringBoot application in Tomcat with Docker

---

## Deploy Spring Boot Application in Standalone Tomcat

---

To deploy Spring boot in standalone tomcat, following changes needs to be done

Either extend main class with ```SpringBootServletInitializer```
```
public class SpringbootTomcatDockerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(SpringbootTomcatDockerApplication.class);
    }

    public static void main(String[] args) {
         SpringApplication.run(SpringbootTomcatDockerApplication.class, args);
    }
}
```
Or Create separate class which extends with ```SpringBootServletInitializer```
```
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootTomcatDockerApplication.class);
	}

}
```
In pom.xml
```
<packaging>war</packaging>
...
<dependency>
    <groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>
...
<build>
  <finalName>SimpleApplication</finalName>
    ....
</build>

```

---

## Docker file configuration

---

```
FROM tomcat:8.5-jdk8-corretto
COPY ./target/SimpleApplication.war /usr/local/tomcat/webapps/SimpleApplication.war
COPY server.xml /usr/local/tomcat/conf/server.xml
EXPOSE 8080
CMD ["catalina.sh", "run"]
```
| Command |                              Usage                               |
|---------|:----------------------------------------------------------------:|
| FROM    | Fetch base image on top of which our custom image is to be build |
| COPY    |           COPY war or given file to destination folder           |
| EXPOSE  |            Expose port 8080 outside Docker container             |
| CMD     |  The command to be executed when the Docker image is run         | 

---

## docker-compose configuration

---

```
version: '3'

services:
  app:
    build: .
    image: spring-boot-tomcat-img
    ports:
      - 8080:8080
```

To build and runn docker-compose

``
$ docker-compose up --build
``

--build is used to build images before starting the container instead of using the existing image

This command will start processing docker-compose.yml file. Since we specified build: . in docker-compose.yml file, it will look for Dockerfile in current directory & build a custom image using the same.

Once the docker container is up, try hitting http://localhost:8080/SimpleApplication/index/Pankaj