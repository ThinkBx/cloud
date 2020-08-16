# 基于java:8镜像进行扩展
FROM java:8

# 指定制作该镜像的作者和联系方式
MAINTAINER fjj

# 指定容器内进程对外开放的端口
EXPOSE 6000

# 定义环境变量
#ENV PARAM=''

# 定义数据卷位置---存放容器共享文件的位置
#VOLUME /tmp

# 修改镜像的命令---安装或配置，每一步会创建一个新的镜像层
#RUN mkdir -p /usr/local/workspace/logs/

# 将maven打成的jar包复制到镜像目录中（相对路径---默认"."就是Dockerfile所在目录）
ADD config-1.0-SNAPSHOT.jar config.jar

# 指定容器启动时，需要执行的命令(运行该jar程序)
ENTRYPOINT java -jar config.jar