package com.nbcedu.function.schoolmaster2.filter;

import java.util.Map;

import org.apache.log4j.Logger;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;
import com.nbcedu.function.documentflow.service.SpringBeanService;
import com.opensymphony.xwork2.ActionContext;

public class SchoolMaster2Loader implements ServiceInfoLoader{

	private static final Logger logger = Logger.getLogger(SchoolMaster2Loader.class);
	@Override
	public Object load(Map<?, ?> params) {
		logger.info(params.get("uid"));
		new  SpringBeanService().getBean(ServiceInfoLoader.class, "documentFlowLoader").load(params);
		return params.get("uid");
	}

}
