#!/bin/sh
# 这个得在系统里配好了 /et/profile
CLASS_PATH=classes:`echo lib/*.jar | tr ' ' ':'`
# 这个得在系统里配好了
JAVA_OPTS=$JAVA_OPTS
# 启动java组件 zookeeper？ 这个得是已经写好的调用
JAVA_CMD="java $JAVA_OPTS -cp $CLASS_PATH com.newnew.wechatservice.support.zk.ZkConfigPublisher"
# 屏幕打印Trying to start publish zk configs using below command:
echo Trying to start publish zk configs using below command:
#  屏幕打印JAVA_CMD
echo $JAVA_CMD
# 执行上面组合出来JAVA_CMD命令
eval $JAVA_CMD
#命令全路径 在classes的上层目录进行使用
#java  -cp  classes:`echo lib/*.jar | tr ' ' ':'` com.xyz.wechatservice.support.zk.ZkConfigPublisher
