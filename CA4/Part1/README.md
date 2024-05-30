# Technical Report for CA4-Part1: Virtualization with Vagrant

## Table of Contents

- [Introduction](#introduction)
- [1. Create the docker file](#1-create-the-docker-file)
    - [1.1. Version 1](#11-version-1)
    - [1.2. Version 2](#12-version-2)
- [2. Publish the image in the Docker Hub](#2-publish-the-image-in-the-docker-hub)
    - [2.1 Clone the repository](#21-clone-the-repository)
        - [2.3.1. Run CA1 project](#231-run-ca1-project)
- [3. Version Control and Documentation](#3-version-control-and-documentation)
- [Conclusion](#conclusion)


## Introduction
This report documents the steps taken to complete Part 1 of the Docker assignment for our course. The objective of this 
assignment is to practice the creation and management of Docker images and containers by utilizing a chat application 
from a previous assignment (CA2). Specifically, we aim to package and execute the chat server within a Docker container, 
demonstrating our ability to create Docker images, publish them to Docker Hub, and connect to the chat server from a 
client running on the host machine.

## 1. Create the docker file
For this assignment, two versions of the Docker solution were creates to understand different approaches to building and 
packaging applications within Docker images:
 * Version 1: Build the chat server directly within the Dockerfile.
 * Version 2: Build the chat server on the host machine and then copy the JAR file into the Docker image.

### 1.1. Version 1
Set the base image for Docker container. It specifies that the container will be built using OpenJDK 17 as the operating
system.
Set the working directory for the container to /app. Any subsequent COPY, ADD, RUN, and other commands will be executed
relative to this directory.
Copy the entire project directory from the host machine into the Docker image.
Install dos2unix package to ensure that the gradlew script is executable within the container.
Change the permissions of the gradlew script to make it executable.
Expose the chat server port to the host machine.
Run the chat server when the container starts.

```Dockerfile
FROM openjdk:17-jdk-slim

LABEL author="Ana Silva"

WORKDIR /app

COPY . /app

RUN apt-get update && apt-get install -y dos2unix

RUN dos2unix ./gradlew

RUN chmod +x gradlew

EXPOSE 59001

CMD ["./gradlew", "runServer"]
```


```bash
cd CA4/Part1/gradle_basic

docker build -f Dockerfile_v1 -t chatserver_ca4_part1_v1 .

docker run -p 59001:59001 chatserver_ca4_part1_v1 
```
```bash
./gradlew runClient
```

### 1.2. Version 2
Set the base image for Docker container. It specifies that the container will be built using OpenJDK 21 as the operating
system.
Set the working directory for the container to /ca4-part1. Any subsequent COPY, ADD, RUN, and other commands will be
executed relative to this directory.
Copy the pre-built JAR file from the host machine into the Docker image.
Expose the chat server port to the host machine.
Run the chat server when the container starts.

```Dockerfile
FROM openjdk:21-jdk-slim

LABEL author="Ana Silva"

WORKDIR /ca4-part1

COPY build/libs/basic_demo-0.1.0.jar /ca4-part1/ca4-part1.jar

EXPOSE 59001

ENTRYPOINT ["java", "-cp", "/ca4-part1/ca4-part1.jar", "basic_demo.ChatServerApp", "59001"]
```


```bash
cd CA4/Part1/gradle_basic

docker build -f Dockerfile_v2 -t chatserver_ca4_part1_v2 .

docker run -p 59001:59001 chatserver_ca4_part1_v2
```

```bash
./gradlew runClient
```
Push the changes to the repository:
```bash
git add .
git commit -m "[FEAT] #25 Create a docker image to execute the chat server"
git push
```

## 2. Publish the image in the Docker Hub
To publish the Docker image to Docker Hub, we need to create a repository on Docker Hub and push the image to it. The
following steps outline the process:

* Create an account on Docker Hub (https://hub.docker.com/).
* Create a new repository on Docker Hub.
* Log in to Docker Hub using the terminal.

```bash
docker login
```

* Tag the Docker image with the repository name with the following command:
```bash
docker tag chatserver_ca4_part1_v1 anasilvaswitch/chatserver_ca4_part1_v1
docker tag chatserver_ca4_part1_v2 anasilvaswitch/chatserver_ca4_part1_v2

```
* Push the Docker image to the repository on Docker Hub with the following command:
```bash
docker push anasilvaswitch/chatserver_ca4_part1_v1
docker push anasilvaswitch/chatserver_ca4_part1_v2

```
* Verify that the images have been successfully pushed to Docker Hub by checking the repository on the Docker Hub website here:
https://hub.docker.com/repository/docker/anasilvaswitch/chatserver_ca4_part1_v1 and https://hub.docker.com/repository/docker/anasilvaswitch/chatserver_ca4_part1_v2

### 3. Version control and Documentation

* Commit your changes and push them to your repository.
```bash
git add .
git commit -m "[DOCS] #26 Created Readme file of CA4 - Part1"
git push origin main
```

* Tag the final submission:
```
git tag -a ca4-part1
git push origin --tags
```

## Conclusion
Through this assignment, we gained hands-on experience with Docker, enhancing our understanding of containerization, 
image creation, and container orchestration. By implementing two different methods of building and packaging the chat 
server, we explored the flexibility and efficiency offered by Docker in managing application environments. We also 
learned how to publish Docker images to Docker Hub, enabling us to share our applications with others and deploy them.

