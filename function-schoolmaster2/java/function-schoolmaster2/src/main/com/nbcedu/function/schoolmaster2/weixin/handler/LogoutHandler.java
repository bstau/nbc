package com.nbcedu.function.schoolmaster2.weixin.handler;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.transfer.handler.AbstractEventHandler;

import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;

/**
 * 注销登陆
 * @author xuechong
 */
public class LogoutHandler extends AbstractEventHandler{

	public static final String LOGOUT_EVENT_KEY = "EVENT_LOGOUT";
	
	private Sm2WeixinUserBiz wxUserBiz ;
	
	@Override
	protected HandleResult doHandle(HandlerInvocation invocation) {
		
		return null;
	}

	@Override
	protected String getEventKey() {
		return LOGOUT_EVENT_KEY;
	}
	
	///////////////////////////
	//////GETTERS&SETTERS//////
	//////////////////////////
	public void setWxUserBiz(Sm2WeixinUserBiz wxUserBiz) {
		this.wxUserBiz = wxUserBiz;
	}

}
