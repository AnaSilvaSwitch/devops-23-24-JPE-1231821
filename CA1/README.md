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
   
   git add . 

   ``` 


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

- creates a tag named 'v1.1.0'

<br><br>

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

   ```

 
3. Tag the version for part 1 and push, and then mark the assignment completion: 

   ```bash 

   git tag ca1-part1 

   git push origin ca1-part1 

   ``` 

<br><br>
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

   git commit -m [FEAT] #4 Created branch email-field and added private Email field in the Employee class" 

   git push origin email-field 

   ``` 



4. Finish the implementations in Employee class and update tests

   ```bash 

   git add . 

   git commit -m [FEAT] #5 Added support for the email field with tests” 

   git push origin email-field 
   
   git checkout main
   
   git merge email-field
   
   git push origin main

   ``` 



5. To debug

   ```bash 

   cd tut/basic 

   ./mvnw spring-boot:run

   ``` 

- visit http://localhost:8080/



6. Merge the feature branch into master and tag the new version:

   ```bash 

   git tag v1.3.0 

   git push origin v1.3.0 

   ```

   - This is useful for preserving the history of a feature branch before it gets integrated into the main branch, making it easier to understand the flow of changes and to revert entire features if needed.

   - As always, every major step should be followed by a tag creation and pushing to origin of such tag


<br><br>
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


5. Merge the bug fix into master and tag:

   ```bash 

   git checkout main 

   git merge fix-invalid-email 

   git push origin main 

   git tag v1.3.1   

   git push origin v1.3.1 

   ``` 
6. Merge the feature branch into master and tag the new version:

   ```bash 

   git tag v1.3.1   

   git push origin v1.3.1

   ```


7. Mark the completion of part 2 and final tag:

   ```bash 

   git tag ca1-part2 

   git push origin ca1-part2 

   ``` 
<br><br>
#### Add README.md



   ```bash 

   touch README.md 

   ``` 



## Analysis of an Alternative Version Control Solution: Mercurial SCM

### Unveiling Mercurial: A Solid Version Control Option
Let's explore Mercurial (Hg), as an alternative to the more widely used Git in the world of version control. Both are Distributed Version Control Systems (DVCS), meaning developers can track and manage changes in their codebase without relying on a central server. While Git currently is the most used, Mercurial offers its own set of advantages.

**Friendlier for Beginners:**
Mercurial is known for its simplicity and straightforward command set witch makes it easier for newcomers to grasp compared to Git's extensive vocabulary. This can be a big advantage if someone is just starting out with version control.

**Branching:**
Both Git and Mercurial allow to create different versions of code (branches) to work on new features without affecting the main project. However, Git's branches are more flexible. They're like temporary drafts that can easily create, merge, and delete. Mercurial's branches are a bit more permanent, and developers often use entire copies of the project (clones) for feature development.

**Performance:**
For massive projects, Git might hold a slight edge in terms of performance due to its efficient branch handling and data storage. However, Mercurial provides sufficient speed for most projects, and its streamlined approach can even make everyday tasks feel faster.

**Tool Integration Landscape:**
Since Git is more popular, there are more tools and websites that integrate seamlessly with it, such as GitHub and GitLab. While Mercurial still works with many tools, the selection is not as vast.

**Choosing Your Version Control Partner:**
For someone who is new to version control or have a smaller project, Mercurial's user-friendliness can be a major benefit. However, for large projects with teams already accustomed to Git's extensive toolset, Git might be the more practical choice.

### Differences in Core Version Control Commands that could be implemented in this assignment
While Git and Mercurial achieve similar goals, their command structures differ. Here's a comparison of some essential commands:

### 1. Initialization:

Git: **git init** - Creates a new Git repository in the current directory.<p>
Mercurial: **hg init** - Initializes a Mercurial repository in the current directory.

### 2. Adding Changes:

Git: **git add <file> or git add** . - Adds specific files or all unstaged changes to the staging area.<p>
Mercurial: **hg add <file> or hg add** . - Directly adds files to the next commit without a separate staging area.

### 3. Committing Changes:

Git: **git commit -m "<message>"** - Creates a new commit with a descriptive message.<p>
Mercurial: **hg commit -m "<message>"** - Similar to Git, creates a commit with a message.

### 4. Viewing Changes:

Git: **git status** - Shows the status of tracked files (modified, staged, or untracked).<p>
Mercurial: **hg status** - Displays the status of tracked files, similar to Git.

### 5. Downloading a Repository (Cloning):

Git: **git clone <url>** - Clones a remote Git repository to a local directory.<p>
Mercurial: **hg clone <url>** - Retrieves a remote Mercurial repository and creates a local copy.

### 6. Pushing Changes:

Git: **git push origin <branch>** - Pushes commits from a local branch to a remote repository named "origin" (default) on a specific branch.<p>
Mercurial: **hg push origin** - Pushes all changes to the default remote repository named "origin".

### 7. Branching:

Git: **git checkout -b <branch_name>** - Creates and switches to a new branch.<p>
Mercurial: **hg branch <branch_name> or hg bookmark <bookmark_name>** - Creates a bookmark for a specific point in the history (similar to a branch).

### 8. Merging:

Git: **git merge <branch_name>** - Merges changes from another branch into the current branch.<p>
Mercurial: **hg merge <branch_name>** - Integrates changes from a bookmark into the default branch, resolving conflicts if necessary.

### 9. Viewing History:

Git: **git log** - Shows the commit history of the repository.<p>
Mercurial: **hg log** - Displays the revision history of the repository.

### 10. Tags:

Git: **git tag <tag_name>** - Creates a tag for a specific commit.<p>
Mercurial: **hg tag <tag_name>** - Similar to Git, creates a tag for a specific revision.

### Sources:

Git SCM - https://git-scm.com/ <p>
Mercurial - https://www.mercurial-scm.org/guide <p>
Atlassian Git Tutorial - https://www.atlassian.com/git/tutorials <p>
A Gentle Introduction to Mercurial - https://wiki.mercurial-scm.org/QuickStart

<br><br>

## Notes

There were several conflicts that had to be resolved during this implementation. Commits were not being made due to missing files from tut. To resolve this, files were added, and two commits were made for this adding. README was added to this timeline to verify that commits were made correctly after resolving the previous situation.

To run the tests, I must open directly the folder tut, otherwise I cannot run the tests.

