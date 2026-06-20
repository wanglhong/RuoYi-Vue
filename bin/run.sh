#!/bin/sh
echo ""
echo "[信息] 使用Jar命令运行Web工程。"
echo ""

cd "$(dirname "$0")"
cd ../ruoyi-admin/target

JAVA_OPTS="-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m"

java -jar $JAVA_OPTS ruoyi-admin.jar
