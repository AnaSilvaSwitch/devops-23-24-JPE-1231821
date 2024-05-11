# Technical Report for CA3-Part1: Virtualization with Vagrant

## Table of Contents

- [Introduction](#introduction)
- [1. Set up the VM](#1-set-up-the-vm)
  - [1.1. Install git](#11-install-git)
  - [1.2. Install maven](#12-install-maven)
  - [1.3. Install java 17](#13-install-java-17)
  - [1.4. Install gradle version 8.6](#14-install-gradle-version-86)
- [2. Project migration and set up](#2-project-migration-and-set-up)
  - [2.1 Clone the repository](#21-clone-the-repository)
  - [2.2 Configure maven wrapper and gradle wrapper](#22-configure-maven-wrapper-and-gradle-wrapper)
  - [2.3 Run the project](#23-run-the-project)
    - [2.3.1. Run CA1 project](#231-run-ca1-project)
    - [2.3.2. Run CA2 - Part1 project](#232-run-ca2---part1-project)
    - [2.3.3 Run CA2 - Part2 project](#233-run-ca2---part2-project)
- [3. Version Control and Documentation](#3-version-control-and-documentation)
- [Problems or issues faced](#problems-or-issues-faced)
- [Conclusion](#conclusion)


## Introduction
The goal of this part of the assignment was to familiarize ourselves with VirtualBox and Ubuntu within a virtual machine environment. We achieved this by creating a Virtual Machine (VM), cloning our individual repository inside the VM, and successfully building and executing projects like the spring boot tutorial basic project and the gradle_basic_demo project.

## 1. Set up the VM
We need to create a new VM with Ubuntu 20.04.3 LTS. We will use VirtualBox to create the VM. The VM must have at least 2GB of RAM and 2 CPUs. We will use the default settings for the rest of the configuration. Then, log in to the VM and install the necessary software.

### 1.1. Install git
```bash
sudo apt update
sudo apt install git
```

### 1.2. Install maven
```bash
sudo apt install maven
```
### 1.3. Install java 17
```bash
sudo apt install openjdk-17-jdk
```
* Verify version:
```bash
java -version
```

### 1.4. Install gradle version 8.6
* Download the gradle version 8.6 and unzip it to the /opt/ directory.
* Add the gradle bin directory to the PATH environment variable.
* Apply the changes to the current shell session by sourcing the ~/.profile file.
* Verify the installation by running the gradle -v command.

```bash
wget https://services.gradle.org/distributions/gradle-8.6-all.zip
sudo unzip -d /opt/ gradle-8.6-all.zip
nano ~/.profile
export PATH=$PATH:/opt/gradle-8.6/bin
source ~/.profile
gradle -v
```
## 2. Project migration and set up

### 2.1 Clone the repository
* Access the terminal through VM's interface.


* Create folder for CA3
* Clone the repository (for this step, the repository must be public).

```bash
mkdir CA3
cd CA3

git init

git clone https://github.com/AnaSilvaSwitch/devops-23-24-JPE-1231821.git
```

### 2.2 Configure maven wrapper and gradle wrapper

* Give permission to execute Maven wrapper and Gradle wrapper.
```bash
chmod +x mvnw
chmod +x gradlew
```

### 2.3 Run the project
#### 2.3.1. Run CA1 project
* Navigate to the project directory.

* Build the project using Maven:
``` bash
./mvnw clean install
```
* Run the project:
``` bash
./mvnw spring-boot:run
```

* Verify that the application is functioning properly by accessing it from the web browser on your host machine, using the IP address of the VM and the designated port set in the project configuration
```bash
ip addr
```
* Put the IP and the port 8080 on the browser address.

#### 2.3.2. Run CA2 - Part1 project
* Navigate to the project directory.


* Build the project using Gradle and run the server:
```bash
./gradlew build
./gradlew runServer
```

* Run the client on the computer terminal (alternatively run the client on the IDE terminal):
```bash
./gradlew runClient --args="192.168.56.10 59001" 
```

#### 2.3.3 Run CA2 - Part2 project
* Navigate to the project directory.

* On the basic folder:
```bash
./gradlew build
./gradlew bootRun
```
* To open project frontEnd on browser, get the VM IP:
```bash
ip addr
```
* Insert the IP and the port 8080 on the browser address.

### 3. Version Control and Documentation
* Commit your changes and push them to your repository.
```
git add .
git commit -m "[DOCS] #21 Create Readme file of CA3 - Part1"
git push origin main
```

* Tag the final submission:
```
git tag -a ca3-part1
git push origin --tags
```

## Problems or issues faced
* The jar file was not being created when running the command ./gradlew build on the CA2 part1 project since it is an ignored file. To solve this, I had to install gradle 8.6.
* To run the client, had to change the argument "localhost" to the IP "192.168.56.10" in the build.gradle file.

## Conclusion
Completing CA3 Part 1 marks the successful setup of a virtual development environment using VirtualBox and the migration of two projects into this environment. With this exercise, we have deepened our understanding of virtualization technology and strengthened our ability to configure and manage distinct development environments. These skills are crucial in professional contexts where aligning development and production environments is essential for effective testing and deployment procedures.