package org.luckystars.weixin.framework.api.impl;

import java.util.Collections;
import java.util.Iterator;


import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.View;

public class DefaultHandlerInvocationImpl implements HandlerInvocation {

	private Iterator<Handler> handlers;
	
	public DefaultHandlerInvocationImpl(Iterator<Handler> handlers){
		this.handlers = handlers;
	}
	
	@Override
	public HandleResult invokeNext() throws Exception {
		HandleResult result = HandleResult.INVOKE_NEXT;
		while(this.handlers!=null&&this.handlers.hasNext()){
			result = handlers.next().handle(this);
			if(result==null){
				throw new NullPointerException("不可返回 null HandleResult");
			}else{
				if(result!=HandleResult.INVOKE_NEXT){break;}
			}
		}
		return result;
	}

	@Override
	public HandlerContext getInvocationContext() {
		return HandlerContext.getContext();
	}

	@Override
	public IncomeMessage getIncomeMsg() {
		return HandlerContext.getContext().getMsg();
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public void stopChain() {
		synchronized (handlers) {
			this.handlers = Collections.EMPTY_LIST.iterator();
		}
	}

	@Override
	public HandleResult createResult(View view) {
		HandleResult result = new DefaultHandleResult();
		result.setView(view);
		return result;
	}

}
