使用说明:如你所见,必要文件
/common/common-appid.properties:
     必要参数:appids
     	/common/wx-xxxxxx.properties 前缀必须为wx-
     		必要参数:wx_appid,wx_appsecret
=========================================================
首次发布 common-appid.properties  appids必填
		当添加新号时候在tomcat下创建common文件夹 将新号的配置文件放入common 进入 WEB_INFO下 然后 执行 脚本publish脚本命令(必须在这里 我也不知道为什么)
		publish:java  -cp  classes:`echo lib/*.jar | tr ' ' ':'` com.newnew.wechatservice.support.zk.ZkConfigPublisher
			然后在WEB_INFO下再执行dowload.sh脚本 实现配置文件更新到工程内.
			download:java  -cp classes:`echo lib/*.jar | tr ' ' ':'` com.newnew.wechatservice.support.zk.ZkConfigSaver ${1:-tmp}
			
     			
     			    
     			