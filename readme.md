如何部署：
  
一、在Linux系统上提前安装好Docker，准备启动MySQL和Redis

```markdown
sudo mkdir -p /app/data/db
docker run -d --name=mysql --restart=always --network=host -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=devicemanagement -v /app/data/db:/var/lib/mysql  mysql:5.6
```

```markdown
docker run -d --name redis --restart=always --network=host -v ./config/redis/redis.conf:/etc/redis/redis.conf redis:5.0 redis-server /etc/redis/redis.conf
```

二、初始化数据库
```markdown
mysql -h 127.0.0.1 -u -p < ./src/main/resources/schema.sql
mysql -h 127.0.0.1 -u -p < ./src/main/resources/data.sql
```

三、打包
```markdown
mvn clean package
```

四、运行（测试环境)
```markdown
java -jar target/devicemanagement-0.0.1-SNAPSHOT.jar
```
其中包含了'admin'和'abc'两个用户，用户密码都为'123456'


