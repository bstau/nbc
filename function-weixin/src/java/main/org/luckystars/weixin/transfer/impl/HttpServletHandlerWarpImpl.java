package org.luckystars.weixin.transfer.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.luckystars.weixin.transfer.HandlerContext;
import org.luckystars.weixin.transfer.HandlerInvocation;
import org.luckystars.weixin.transfer.HandlerWarp;
import org.luckystars.weixin.transfer.interfaces.Handler;
import org.luckystars.weixin.transfer.msg.MsgFactory;

public class HttpServletHandlerWarpImpl implements HandlerWarp{

	private static final Logger logger = Logger.getLogger(HttpServletHandlerWarpImpl.class);
	
	private HandlerInvocation invocation;
	
	public HttpServletHandlerWarpImpl(HttpServletRequest req,HttpServletResponse resp){
		
		createHandlerContext(req,resp);
		this.invocation = warpInvocation(req,resp);
		
	}
	
	private void createHandlerContext(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			HandlerContext ctx = 
				new HandlerContext(MsgFactory.build(getRawStr(req)), resp.getOutputStream());
			HandlerContext.putContext(ctx);
		} catch (IOException e) {
			logger.error("获取response输出流出错",e);//well ....╮(╯_╰)╭
		}
	}

	
	private String getRawStr(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	private HandlerInvocation warpInvocation(HttpServletRequest req,
			HttpServletResponse resp) {
		
		return null;
	}

	@Override
	public void handle() {
		try {
			
			Handler.HandleResult result = invocation.invokeNext();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			///释放上下文资源
			HandlerContext.cleanContext();
		}
	}
	
}
