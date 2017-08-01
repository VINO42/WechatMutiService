##WechatMutiService?
  支持多公众号的一个javaWeb工程
##使用说明

* 必要文件
    *  /common/common-appid.properties
        * 必要参数:appids 为以逗号分隔的MD5加密的appid
    *  /common/wx-xxxxxx.properties(前缀必须为 wx-) 
        * 必要参数:wx_appid,wx_appsecret
* 首次部署 common-appid.properties appids 必填
* 当添加新号时候在 tomcat根目录 下创建 common 文件夹,将新号的配置文件放入 common
* WEB_INFO 下执行 脚本 publish 命令java -cp classes:echo lib/*.jar | tr ' ' ':'
* 在 WEB_INFO 下再执行 dowloadjava -cp classes:echo lib/*.jar | tr ' ' ':' com.newnew.wechatservice.support.zk.ZkConfigSaver "/common"
 ###Controller 入参必要参数为md5(appid)
##TODO:扩展dubbo