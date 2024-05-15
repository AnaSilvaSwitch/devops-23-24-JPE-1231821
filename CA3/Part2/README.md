# Technical Report for CA3-Part2: Virtualization with Vagrant

## Table of Contents

- [Introduction](#introduction)
- [1. Copy the Vagrantfile](#1-copy-the-vagrantfile)
- [2. Update the Vagrantfile](#2-update-the-vagrantfile)
- [3. Running the VM's](#3-running-the-vms)
- [4. Alternative using VMware](#4-alternative-using-vmware)
    - [4.1 Download and Install VMware Workstation Player](#41-download-and-install-vmware-workstation-player)
    - [4.2 Install the VMware Vagrant Plugin](#42-install-the-vmware-vagrant-plugin)
    - [4.3 Update the Vagrantfile for VMware](#43-update-the-vagrantfile-for-vmware)
    - [4.4 Running the VM's with VMware](#44-running-the-vms-with-vmware)
- [5. Create a README file and tag the assignment](#5-create-a-readme-file-and-tag-the-assignment)
- [Conclusion](#conclusion)


## Introduction
For this class assignment, we delve into the realm of virtualization using Vagrant, a powerful tool for creating and 
managing virtual environments. Vagrant simplifies the process of setting up development environments by automating the 
creation and configuration of virtual machines (VMs) through a straightforward configuration file.

## 1. Copy the Vagrantfile
Firstly, we need to copy the Vagrantfile from https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/ to the 
CA3-Part2 folder of our repository. This file contains the instructions to set up two VMs (db and web).

```bash
mkdir CA3/Part2
cd CA3/Part2
curl -o Vagrantfile https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/raw/master/Vagrantfile && cp Vagrantfile ./Vagrantfile
```

Commit the changes to the repository.

```bash
git add .
git commit -m "[FEAT] #22 Copied the vagrantfile from https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/"
git push
```

## 2. Update the Vagrantfile
Update the vagrantfile based on the specific requirements of the project.
Note: the repository must be public to be able to clone it.

```ruby
# See: https://manski.net/2016/09/vagrant-multi-machine-tutorial/
# for information about machine names on private network
Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/focal64"
  config.ssh.insert_key = false

  # This provision is common for both VMs
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
        openjdk-17-jdk-headless
    # ifconfig
  SHELL

  #============
  # Configurations specific to the database VM
  config.vm.define "db" do |db|
    db.vm.box = "ubuntu/focal64"
    db.vm.hostname = "db"
    db.vm.network "private_network", ip: "192.168.56.11"

    # We want to access H2 console from the host using port 8082
    # We want to connect to the H2 server using port 9092
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    # We need to download H2
    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
    SHELL

    # The following provision shell will run ALWAYS so that we can execute the H2 server process
    # This could be done in a different way, for instance, setting H2 as as service, like in the following link:
    # How to setup java as a service in ubuntu: http://www.jcgonzalez.com/ubuntu-16-java-service-wrapper-example
    #
    # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL
  end

  #============
  # Configurations specific to the webserver VM
  config.vm.define "web" do |web|
    web.vm.box = "ubuntu/focal64"
    web.vm.hostname = "web"
    web.vm.network "private_network", ip: "192.168.56.10"

    # We set more ram memory for this VM
    web.vm.provider "virtualbox" do |v|
      v.memory = 1024
    end

    # We want to access tomcat from the host using port 8080
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    web.vm.provision "shell", inline: <<-SHELL, privileged: false
      # sudo apt-get install git -y
      # sudo apt-get install nodejs -y
      # sudo apt-get install npm -y
      # sudo ln -s /usr/bin/nodejs /usr/bin/node
      sudo apt install -y tomcat9 tomcat9-admin
      # If you want to access Tomcat admin web page do the following:
      # Edit /etc/tomcat9/tomcat-users.xml
      # uncomment tomcat-users and add manager-gui to tomcat user

      # Change the following command to clone your own repository!
      git clone https://github.com/AnaSilvaSwitch/DevOps-23-24-JPE-1231821.git
      cd DevOps-23-24-JPE-1231821/CA2/Part2
      chmod u+x gradlew
      ./gradlew clean build
      nohup ./gradlew bootRun > /home/vagrant/spring-boot-app.log 2>&1 &
      # To deploy the war file to tomcat9 do the following command:
      # sudo cp ./build/libs/basic-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps
    SHELL
  end
end
```

Commit the changes to the repository.

```bash
git add .
git commit -m "[FEAT] #23 Updated the vagrantfile configuration for my gradle version of the spring application"
git push
```

## 3. Running the VM's
The virtual machines can be created and provisioned using the `vagrant up` command. The `vagrant up` command reads the 
Vagrantfile, creates virtual machines, and runs provisioning scripts to install software packages and configure the VMs 
accordingly.

After running `vagrant up`, we can access the web server by opening a web browser and typing in the IP address of the web 
server virtual machine, followed by `:8080` in the address bar. Similarly, to access the H2 database console, open a web 
browser and enter the IP address of the database virtual machine, followed by either `:8082` or `:9092` in the address bar.


## 4. Alternative using VMware

### 4.1 Download and Install VMware Workstation Player
VMware Workstation Player is a free virtualization software that allows to run multiple operating systems on a single
computer. It can be downloaded VMware Workstation Player from the official VMware website (see sources).

### 4.2 Install the VMware Vagrant Plugin
To use VMware with Vagrant, we need to install the VMware Vagrant plugin. We can do this by running the following command:

```bash
vagrant plugin install vagrant-vmware-workstation
```
Activate the plugin by running the following command:

```bash
vagrant plugin license vagrant-vmware-workstation ~/path/to/license.lic
```
### 4.3 Update the Vagrantfile for VMware
To use VMware with Vagrant, we need to update the Vagrantfile to specify the provider as VMware.

    ```ruby
    Vagrant.configure("2") do |config|
      config.vm.provider "vmware_workstation" do |v|
      v.vmx["memsize"] = "2048" # Memory alocated to the VM / Adjust as needed
      v.vmx["numvcpus"] = "2"   # Number of CPUs / Adjust as needed
    end
    
    config.vm.box = "hashicorp/bionic64"
    config.ssh.insert_key = false
    
    # This provision is common for both VMs
    config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
    openjdk-17-jdk-headless
    # ifconfig
    SHELL
    
    #============
    # Configurations specific to the database VM
      config.vm.define "db" do |db|
         db.vm.provider "vmware_workstation" do |v|
            v.vmx["memsize"] = "2048" # Memory alocated to the VM / Adjust as needed
            v.vmx["numvcpus"] = "1"   # Number of CPUs / Adjust as needed
         end

         db.vm.box = "hashicorp/bionic64"
         db.vm.hostname = "db"
         db.vm.network "private_network", ip: "192.168.56.11"

         # We want to access H2 console from the host using port 8082
         # We want to connet to the H2 server using port 9092
         db.vm.network "forwarded_port", guest: 8082, host: 8082
         db.vm.network "forwarded_port", guest: 9092, host: 9092

         # We need to download H2
         db.vm.provision "shell", inline: <<-SHELL
            wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
         SHELL

         # The following provision shell will run ALWAYS so that we can execute the H2 server process
         # This could be done in a different way, for instance, setting H2 as as service, like in the following link:
         # How to setup java as a service in ubuntu: http://www.jcgonzalez.com/ubuntu-16-java-service-wrapper-example
         #
         # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
         db.vm.provision "shell", :run => 'always', inline: <<-SHELL
            java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
         SHELL
      end

      #============
      # Configurations specific to the webserver VM
      config.vm.define "web" do |web|
         web.vm.provider "vmware_workstation" do |v|
            v.vmx["memsize"] = "2048" # Memory alocated to the VM / Adjust as needed
            v.vmx["numvcpus"] = "2"   # Number of CPUs / Adjust as needed
         end

         web.vm.box = "hashicorp/bionic64"
         web.vm.hostname = "web"
         web.vm.network "private_network", ip: "192.168.56.10"

         # We want to access tomcat from the host using port 8080
         web.vm.network "forwarded_port", guest: 8080, host: 8080

         web.vm.provision "shell", inline: <<-SHELL, privileged: false
            # sudo apt-get install git -y
            # sudo apt-get install nodejs -y
            # sudo apt-get install npm -y
            # sudo ln -s /usr/bin/nodejs /usr/bin/node
            sudo apt install -y tomcat9 tomcat9-admin
            # If you want to access Tomcat admin web page do the following:
            # Edit /etc/tomcat9/tomcat-users.xml
            # uncomment tomcat-users and add manager-gui to tomcat user

            # Change the following command to clone your own repository!
            git clone https://github.com/AnaSilvaSwitch/devops-23-24-JPE-1231821.git
            cd devops-23-24-JPE-1231821/ca2/part2
            chmod u+x gradlew
            ./gradlew clean build
            nohup ./gradlew bootRun > /home/vagrant/spring-boot-app.log 2>&1 &
            # To deploy the war file to tomcat9 do the following command:
            # sudo cp ./build/libs/basic-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps
         SHELL
      end
    end
    ```
### 4.4 Running the VM's with VMware
To create and provision the virtual machines using VMware, we can use the `vagrant up --provider=vmware_workstation` command.

```bash
vagrant up --provider=vmware_workstation
```

## 5. Create a README file and tag the assignment
Create a README.md file in the CA3/Part2 folder with the technical report for the assignment. Tag the assignment with the
appropriate labels.

```bash
touch README.md
git add .
git commit -m "[DOCS] #24 Created the README.md file for CA3-Part2 assignment"
git push
```
```bash
git tag -a CA3-Part2 -m "ca3-part2"
git push origin CA3-Part2
```

## Conclusion
Unlike the initial part of our assignment where VirtualBox was employed for VM creation, Vagrant brings a level of 
automation that streamlines the setup process significantly. Manual configurations, software downloads, and project 
setup steps are replaced with a structured, repeatable process defined within the Vagrantfile.

Creating a Vagrantfile is pivotal to this automation process. It's a Ruby-based configuration file that specifies the 
requirements for our virtual environment, including software installations, networking setups, and any necessary scripts 
to be executed during VM provisioning. By defining these requirements in a Vagrantfile, we can easily replicate the
environment across different machines, ensuring consistency and reproducibility.

Vagrant also supports multiple providers, such as VirtualBox, VMware, and Docker, allowing users to choose the best
platform for their needs. In this assignment, we explored the use of VirtualBox and VMware as providers for Vagrant,
demonstrating the flexibility and versatility of the tool.

In conclusion, Vagrant is a versatile tool that simplifies the process of creating and managing virtual environments.
Whether we're using VirtualBox or VMware, Vagrant provides a consistent and automated way to set up development
environments, making it easier to collaborate, test, and deploy applications.


### Sources
https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/

Vagrant:

https://developer.hashicorp.com/vagrant/docs

VMware:

https://www.vmware.com/products/workstation-player/workstation-player-evaluation.html

https://docs.vmware.com/pt/
