package com.newnew.wechatservice.support.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newnew.wechatservice.support.config.WxConfig;
import com.newnew.wechatservice.support.config.WxXxConfig;
import com.newnew.wechatservice.support.handler.AbstractHandler;
import com.newnew.wechatservice.support.handler.MenuHandler;
import com.newnew.wechatservice.support.handler.MsgHandler;
import com.newnew.wechatservice.support.handler.SubscribeHandler;
import com.newnew.wechatservice.support.handler.UnsubscribeHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1LocationHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1MenuHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1MsgHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1SubscribeHandler;
import com.newnew.wechatservice.support.handler.appid1.Gzh1UnSubscribeHandler;

/**
 * 
 * @author Binary Wang
 *
 */
@Service("xXWxService")
public class XXWxService extends BaseWxService {
  @Autowired
  private WxXxConfig wxConfig;
//TODO注入 helper 通过helper 获得相关的配置信息从zk中 Builder appid +constants_key 获得value static内部类
  @Autowired
  private Gzh1LocationHandler locationHandler;
  
  @Autowired
  private Gzh1MenuHandler menuHandler;
  
  @Autowired
  private Gzh1MsgHandler msgHandler;
  
  @Autowired
  private Gzh1UnSubscribeHandler unSubscribeHandler;
  
  @Autowired
  private Gzh1SubscribeHandler subscribeHandler;

  @Override
  protected WxConfig getServerConfig() {
    return this.wxConfig;
  }

  @Override
  protected MenuHandler getMenuHandler() {
    return this.menuHandler;
  }

  @Override
  protected SubscribeHandler getSubscribeHandler() {
    return this.subscribeHandler;
  }

  @Override
  protected UnsubscribeHandler getUnsubscribeHandler() {
    return this.unSubscribeHandler;
  }

  @Override
  protected AbstractHandler getLocationHandler() {
    return this.locationHandler;
  }

  @Override
  protected MsgHandler getMsgHandler() {
    return this.msgHandler;
  }

  @Override
  protected AbstractHandler getScanHandler() {
    return null;
  }

}
