#!/bin/bash  
echo "Stopping SpringBoot Application" 
pid=$(ps -ef|grep -i whqServer.jar |grep -v "grep"|awk '{print $2}') 
echo "获取pid成功"+$pid 
  if [ -n "$pid" ];
   then 	kill -9 $pid
    echo '解除端口占用' 
    fi
