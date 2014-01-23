package com.nbcedu.function.weixin.transfer.handler;

import com.nbcedu.function.weixin.transfer.MsgContext;
import com.nbcedu.function.weixin.transfer.interfaces.MsgHandler;

public abstract class AbstractMsgHandler implements MsgHandler{

	@Override
	public boolean test(MsgContext msg) {
		return msg.getReply()!=null;
	}
	
	

}
