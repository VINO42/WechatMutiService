#!/bin/sh
CLASS_PATH=classes:`echo lib/*.jar | tr ' ' ':'`

JAVA_OPTS=$JAVA_OPTS

OUTPUT=${1:-tmp}

JAVA_CMD="java $JAVA_OPTS -cp $CLASS_PATH com.newnew.wechatservice.support.zk.ZkConfigSaver $OUTPUT"

echo Trying to download configs using below command:
echo $JAVA_CMD

eval $JAVA_CMD
#命令全路径 在classes的上层目录进行使用
#java  -cp classes:`echo lib/*.jar | tr ' ' ':'` com.xyz.wechatservice.support.zk.ZkConfigSaver ${1:-tmp}
