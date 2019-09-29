#!/bin/bash
 echo "授予当前用户权限"
 chmod 777 /www/whqServer.jar
 echo "执行......"
 BUILD_ID=dontkillMe nohup java -jar /www/whqServer.jar &