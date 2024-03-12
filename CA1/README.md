# Technical Report for CA1: Version Control with Git



## Introduction



This technical report outlines the progress and insights gained from Class Assignment 1 (CA1), which centers on implementing version control best practices using Git and investigating an alternative version control system for comparative analysis. The assignment consists of two phases: initially operating directly on the master branch, followed by the integration of branching methodologies for feature enhancements and bug resolution.



## Part 1



To implement the initial setup and a new feature directly on the master branch.



### Implementation Steps



#### Setup and Initial Commit



1. Initialize the Git repository:

   ```bash 

   git init 

   ``` 

    - adds a ".git" directory to the current directory (the added directory contains alal the information required for the repository work process)



2. Add all files to the staging area:

   ```bash 

   git add . 

   ``` 

    - before being ready to be commited and then pushed to the remote repository, changes must be added to a staging area, covered by this command. The "." notation indicates that ALL the unstaged files in the repository directory should be staged.



3. Create folder CA1:

   ```bash 

   mk dir CA1 

   ``` 



   ```bash 

   git add . 

   ``` 

- before being ready to be commited and then pushed to the remote repository, changes must be added to a staging area, covered by this command. The "." notation indicates that ALL the unstaged files in the repository directory should be staged.



4. Cloning and adding tut-react-and-spring-data-rest:

    - before we need to change to the folder/directory where our local repository is located.

   ```bash 

   git clone git@github.com:spring-guides/tut-react-and-spring-data-rest.git 

   git add . 

   ``` 



5. Move the application into the new CA1 folder:

   ```bash 

   mv tut-react-and-spring-data-rest . /CA1 

   ``` 

6. Create repository at GitHub.com with name *“devops-23-24-JPE-1231821”*



7. Link the remote of repository on GitHub to git:

   ```bash 

   git remote add origin git@github.com:Departamento-de-Engenharia-Informatica/devops-23-24-JPE- 1231821.git 

   git status 

git add .



   ``` 

  

8. Commit the added files: 

   ```bash 

   git commit -m "[INIT] #1 Initial commit and move tut int CA1 folder" 

   ``` 

- creates a new commit, containing the current contents of the index (the staged changes to the files in the repository) and the given log message (after "-m") describing the changes.



9. Push the commit to the remote repository:

   ```bash 

   git push -u origin master 

   ``` 



10. Tag the initial version of the application as `v1.1.0` and push the tag to the remote repository:

   ```bash 

   git tag v1.1.0 

   git push origin v1.1.0 

   ``` 

- creates a tag named 'v1.1.0', holding the message stated above (after "-m")



#### New Feature – Job Title and Job Years and Completion



1. Creation of Issues

    - In the remote repository, issues should be created for this new feature.



2. After implementing the feature and tests, tag the version, add the changes and then commit:

   ```bash 

   git add . 

   git tag v1.2.0  

   git push 

   git push origin v1.2.0 

   git commit -m " Add a new fields to the application and tests.”  

```bash 

 

3. Tag the version for part 1 and push, and then mark the assignment completion: 

   ```bash 

   git tag ca1-part1 

   git push origin ca1-part1 

   ``` 



#### Add README.md



   ```bash 

   Touch README.md 

   ``` 





###Notes:

There were several conflicts that had to be resolved during this implementation. Commits were not being made due to missing files from tut. To resolve this, files were added, and two commits were made for this adding. README was added to this timeline to verify that commits were made correctly after resolving the previous situation.

To run the tests, I must open the folder tut, otherwise I cannot run the tests.



## Part 2: Using Branches for Development



To use branches for developing new features and fixing bugs, with the master branch serving as the stable version base.



### Implementation Steps



#### Feature Development - Email Field



1. Create issues in the remote repository to be further resolved during the Assignment

    - after creating the issues, you can associate them with specific project branches (to be created)

    - in the merging processes, the remote repository will look for indications in the commit messages of the merged branches that these commits have fixes issues #X.



2. Create and switch to the `email-field` branch:

   ```bash 

   git checkout -b email-field 

   ``` 

    - forces the creation of a new branch (checkout command changes the branch being worked on and the "-b" notation enables branch creation if the branch does not exist)



3. After implementing the feature and conducting tests, add and commit the changes, then push the branch:

   ```bash 

   git add . 

   git commit -m "[FEAT] #4 Created branch email-field and added private Email field in the Employee class" 

   git push origin email-field 

   ``` 



4. Finish the implementations in Employee class and update tests

```bash 

git add . 

 git commit -m “[FEAT] #5 Added support for the email field with tests” 

git push origin email-field 

``` 



5. To debug

   ```bash 

   cd tut/basic 

./mvnw spring-boot:run

     ``` 

- visit http://localhost:8080/



6. Merge the feature branch into master and tag the new version:

   ```bash 

   git checkout main 

   git merge email-field 

   git push origin main 

   git tag v1.3.0 

   git push origin v1.3.0 

   ``` 

    - changes to the master branch and merges the previous changes made in the email branch with the master branch (all conflicts should be manually resolved)

    - the "--no-ff" notations stops the merge from making a "fast-forward merge" (happens when the current branch hasn't diverged from the target branch you're merging in. Instead of creating a new commit to represent the merge, Git just moves the current branch forward until it's at the same commit as the target branch.)

    - This is useful for preserving the history of a feature branch before it gets integrated into the main branch, making it easier to understand the flow of changes and to revert entire features if needed.

    - As always, every major step should be followed by a tag creation and pushing to origin of such tag



#### Bug Fix - Valid Email Check



1. Create the issues regarding this chapter

2. Create and switch to the `fix-invalid-email` branch:

   ```bash 

   git checkout -b fix-invalid-email 

   ``` 



3. After implementing the feature, push the branch:

   ```bash 

   git add . 

   git commit -m "[FEAT] #6 Created branch fix-invalid-email" 

   git push origin fix-invalid-email 

   ``` 





4. After fixing and testing, add, commit, and push the bug fix:

   ```bash 

   git add . 

   git commit -m "[FEAT] #7 Made the fixes and tests to prevent emails without @ on it" 

   git push origin fix-invalid-email 

   ``` 



5. Merge the bug fix into master and tag:

   ```bash 

   git checkout main 

   git merge fix-invalid-email 

   git push origin main 

   git tag v1.3.1   

   git push origin v1.3.1 

   ``` 



6. Mark the completion of part 2:

   ```bash 

   git tag ca1-part2 -m "End of CA1 Part 2" 

   git push origin ca1-part2 

   ``` 