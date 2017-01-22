package com.ai.platform.modules.sys.listener;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.platform.modules.sys.service.SystemService;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		try{
			 ICacheClient _jedisGnarea = MCSClientFactory.getCacheClient("com.ai.platform.common.cache.gnarea");
			_jedisGnarea.del("areaTreeALL","OfficeAllList","findTreeInit");
		}catch(Exception ex){
			logger.error("缓存===com.ai.platform.common.cache.gnarea===连接失败");
			return super.initWebApplicationContext(servletContext);
		}
		
		if (!SystemService.printKeyLoadMessage()){
			return null;
		}
		
		
		
		return super.initWebApplicationContext(servletContext);
	}
}
