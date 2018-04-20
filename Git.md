# Git 

Sources：
- [GitHub - Interview-Notebook](https://github.com/CyC2018/Interview-Notebook/blob/master/notes/Git.md#git-%E5%B7%A5%E4%BD%9C%E6%B5%81)

## 1.GitHub基本操作：
create a new repository on GitHub and upload files
```
git init 
: Set git working directory in local directory, create .git

git add README.md 
: optional, add documentational page for github

git add .
: add particular changed files or add all to stage

git commit -m "first commit"
: commit the stage change to master/branches

git remote add origin https://github.com/username/repositoryname.git
: add remote repository via url links, git remote add <shortname> <url>

git push -u origin master
: share your changes, git push <remote> <branch>
```

clone/download files from GitHub 
```
git clone https://github.com/username/repositoryname.git
: clone a repository into a newly created directory. 
creates a remote called ‘origin’, sets up a local branch and creates remote-tracking branches for all the branches in the repo

git fetch <remote>
: creates a local copy of a remote branch, will not merge 

git pull <remote><branch>
:git pull runs ‘fetch’ then a ‘merge’ by default

```

## 2. Git概念
### 2.1 中心服务器 & 分布式
1. SVN(subversion) 和 git一样是开放源代码的版本控制系统，SVN是集中式的（需要联网、从服务器获取代码），Git是分布式的，不需要联网在本地也可以修改/保存版本。
2. GitHub是Git的中心服务器的一种

### 2.2 工作流
1 Work Directory = 本地文件夹 .git
（add/checkout）
2 Stage 缓存区
(commit/reset)
3 History (master/branches)

### 2.3 冲突 Merge Conflict
当两人对同一分支修改，并且合并分支时会出现冲突。
解决冲突：
先pull 再push
使用 <<<<<<< ，======= ，>>>>>>> 标记出不同分支的内容，只需要把不同分支中冲突部分修改成一样就能解决冲突。

### 2.4 储藏 Stashing
在分支被修改但是未提交、且用户切换分支的时候，两个分支都会被修改。
需要先git stash 储存当前分支修改，再进行切换
```
$ git stash
Saved working directory and index state \ "WIP on master: 049d078 added the index file"
HEAD is now at 049d078 added the index file (To restore them type "git stash apply")
```

## 3. Git CheatSheet

<div align="center"> <img src="/git_cheat_sheet.jpg"/> </div><br>



