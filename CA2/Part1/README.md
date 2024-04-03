# Class assignment 2
   This is the technical report for the Class Assignment 2 (CA2) - Part 1. It covers the implementation of build tools using Gradle, including the execution of a server, unit testing, and file management tasks.


## Task 1: Build Tools with Gradle

1. Create folder `CA2` in your repository
  ```bash
   mkdir CA2/Part1
  ```


2. Clone repository to your local machine
  ```bash
   git clone https://bitbucket.org/pssmatos/gradle_basic_demo/
   ```

3. **Committing the Changes**

  ```bash
   git add .
   git commit -m "#10 Add folders and gradle_basic_demo closes #10"
   git push
  ```

4. See README and follow the instructions
  ```bash
   ./gradlew build
  ```

   * Run the server
  ```bash
   java -cp build/libs/basic_demo-0.1.0.jar basic_demo.ChatServerApp <server port>
   ```

   *. Open another terminal and run the client
  ```bash
   ./gradlew runClient
   ```
   * Open another terminal and run the client again

   * Open chatter and chat between clients


## Task 2: Execute the Server


1. Create an issue for this task 

2. Modify the `build.gradle` file to include a custom task named `runServer` that uses the `JavaExec` type


```groovy
task runServer(type:JavaExec, dependsOn: classes){
   group = "DevOps"
   description = "Launches a chat server that listens on port 59001"

   classpath = sourceSets.main.runtimeClasspath

   mainClass = 'basic_demo.ChatServerApp'

   args '59001'
}
```

3. Execute the server
```bash
./gradlew runServer
```

4. **Commit the Changes**
  ```bash
  git add .
  git commit -m "#12 Implemented gradle task to execute the server closes #12"
  git push
  ```

## Task 3: Simple Unit Test

1. Create an issue for this task

2. **Unit Test Implementation**:
   Implement the provided unit test in `src/test/java/basic_demo/AppTest.java` as follows:


  ```java
  package basic_demo;


import org.junit.Test;
import static org.junit.Assert.*;


public class AppTest {
   @Test public void testAppHasAGreeting() {
      App classUnderTest = new App();
      assertNotNull("app should have a greeting", classUnderTest.getGreeting());
   }
}
  ```

3. **Add JUnit Dependency**:
   Ensure JUnit 4.12 is added to `build.gradle` for the test to run
  ```java
  dependencies {
   testImplementation 'junit:junit:4.12'
}
  ```

4. **Run the Test**
  ```bash
  ./gradlew test
  ```

5. **Commit the Changes**
  ```bash
  git add .
  git commit -m "#13 Implemented a simple unit test and updated gradle script with junit 4.12"
  git push
  ```

## Task 4: Backup of Sources

1. Create an Issue for this task
2. Create a task in the `build.gradle` file to backup source files to a `backup` directory

```groovy
task backupSource(type: Copy) {
   group = "DevOps"
   description = "Copy of the source code to a backup folder"
   from 'src'
   into 'backup'
}
```

3. **Execute the backup task**
```bash
./gradlew backupSource
```
4. **Commit the Changes**
  ```bash
  git add .
  git commit -m "#14 Implemented a gradle task to make a backup of the sources of the application"
  git push
  ```

## Task 5: Archive of Sources
1. Create an Issue for this task
2. Add a task to create a zip archive of the source files

```groovy
task zipSources(type: Zip) {
   group = "DevOps"
   description = "Creates a zip archive of the source code"
   from 'src'
   archiveFileName = 'source.zip'
   destinationDirectory = file('backup/')
}
```

3. Execute the archive task
```bash
./gradlew zipSources
```
4. **Tag the Repository**
 ```bash
 git tag ca2-part1
 git push origin ca2-part1
 ```
5. **Commit the Changes**
  ```bash
git add .
git commit -m "#15 Implemented a gradle task to create a Zip archive of the sources of the application and add final tag"
git push
  ```

## Finalizing the Assignment

1. **Add and fill README.md**:
In the folder `CA2/Part1` create a file named `README.md` and add the content described above

  ```bash
touch README.md
git add .
git commit -m "#11 Create README file to Part1 of CA2"
git push
  ```

## Conclusion


In conclusion, this assignment provided a comprehensive opportunity to delve into the world of build tools with Gradle. Throughout the process, we successfully engaged with a simple example application, gaining hands-on experience with Gradle's functionalities and capabilities.