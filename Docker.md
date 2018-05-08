# Docker

Source:
[Docker — 从入门到实践](https://yeasy.gitbooks.io/docker_practice/content/)
[Nginx 容器教程](http://www.ruanyifeng.com/blog/2018/02/nginx-docker.html)
[Docker基本原理](https://lark.alipay.com/aone/docker/tovu4b)

## 1. Docker
- What
Docker: Google用Go研发的开源虚拟机，基于 OS 层的虚拟化技术之上的容器引擎，实现对进程的封装隔离。开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上。

- Why 
比传统虚拟机更快、更适合拓展/迁移/配置。传统的虚拟机技术是虚拟出一套硬件后，在其上运行一个完整操作系统，在该系统上再运行所需要的应用进程；而容器内的应用进程直接运行与宿主的内核，容器没有自己的内核，也没有硬件虚拟，容器是轻量级的虚拟化技术。
- 

### 1.1 概念：
镜像 Image: OS的root 文件系统，分层存储
容器 Container: 镜像运行的实体 like class & object, 容器存储层, 数据读写应使用数据卷volumn
仓库 Repository: Docker Registry服务 (在其它服务器上使用镜像所需要的一个集中的存储、分发镜像的服务)包含多个仓库，每个仓库包含多个标签Tags, 每个标签=一个镜像

### 1.2 安装：
```
Install Homebrew:
$ /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

Install Homebrew Cask:
$ brew tap caskroom/cask

Install Docker
$ brew cask install docker
```

或者点击下载：[Stable](https://download.docker.com/mac/stable/Docker.dmg) [Edge](https://download.docker.com/mac/edge/Docker.dmg)

检查安装：

```
$ docker --version

$ docker-compose --version

$ docker-machine --version

```

## 2. Nginx

Nginx: an HTTP and reverse proxy server.
Nginx is created to solve C10k problem (concurrently handling ten thousand connections).
Nginx is a container under Docker.

```
Install Nginx (from docker):
$ docker pull nginx

Install Nginx from brew:
$ brew install nginx


```



Build a web server using Nginx:

$ docker container run -d -p 127.0.0.2:8080:80 --rm --name mynginx nginx
```
-d : 后台运行
-p : 80端口 映射到 127.0.0.2:8080
--rm : 容器停止运行后，自动删除容器文件

使用浏览器访问： 127.0.0.2:8080， 可以看到Nginx欢迎页

```
stop container:

$ docker container stop mynginx
```


