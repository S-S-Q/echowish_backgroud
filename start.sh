#!/bin/bash
mvn package -DskipTests
mv -f target/android_backgroud-0.0.1-SNAPSHOT.jar  /root/docker/

if [ -e "/root/docker/" ];
then
	cd /root/docker/
else
	mkdir /root/docker/
	cd /root/docker/	
fi


#判断关闭文件是否存在
if [ -e "/root/docker/stop.sh" ];
then
	./stop.sh
else
	echo "./stop.sh文件不存在"
fi



./start.sh
