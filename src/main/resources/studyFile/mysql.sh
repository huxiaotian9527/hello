/*----------------ubuntu上mysql操作指引，我用的ubuntu18，mysql5.7----------------*/
1.在ubuntu服务器上通过apt更新包索引
sudo apt update

2.安装mysql
sudo apt install mysql-server

/*-------------可以不做----------*/
3.运行安全脚本，配置mysql安全措施(过程中会设置mysql密码)：
sudo mysql_secure_installation

4.无密码登陆
sudo mysql

6.新增用户，并赋权限：*.*表示所有库所有表
grant all privileges on *.* to 'hutiantian'@'%' identified by 'Hu_123456';
flush privileges;       --刷新

7.修改mysql配置文件 bind-address为0.0.0.0，并重启mysql
cd /etc/mysql/mysql.conf.d
vi mysqld.cnf
systemctl restart mysql

8.mysql5.7修改root用密码登陆
update user set authentication_string = password('root123'), plugin='mysql_native_password',password_expired = 'N', password_last_changed = now() where user = 'root';


/********************************ubuntu安装jdk环境************************************************/
1.下载jdk1.8tar包 https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2.上传到指定目录，例如 /usr/local
cd /usr/local
rz jdk-8u211-linux-x64.tar.gz

3.解压
tar -xzvf jdk-8u211-linux-x64.tar.gz

4.设置环境变量
vi ~/.bashrc
将以下命令粘贴至文件最后
export JAVA_HOME=/usr/local/jdk1.8.0_211
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=.:${JAVA_HOME}/bin:$PATH
生效
source ~/.bashrc

5.启动springboot项目，后面要加个 & 符号，这样ctrl+c就会不会关闭boot项目
java -jar bird-0.0.1-SNAPSHOT.jar &