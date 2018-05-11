/*
 * 文件名称: SmsInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-6
 * 修改内容: 
 */
package com.kensure.ktl.taobao.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.kensure.time.MyTimerTasker;


/**
 * 淘宝页面爬虫定时服务类
 * @author fankd created on 2017-9-6
 * @since 
 */
@Service
public class TaobaoTimerService implements MyTimerTasker{
	
	private final static Logger LOGGER = Logger.getLogger(TaobaoTimerService.class);
	


	@Override
	public void run() {
		try {
		
			
			
		} catch (Throwable e) {
			LOGGER.error(e);
			e.printStackTrace();
		} finally {
		
		}
	}
	
	
	@Override
	public Date getFirstTime() {
		return new Date(System.currentTimeMillis() + 5000);
	}

	@Override
	public long getPeriod() {
		return 1000 * 60 * 60;
	}

	@Override
	public boolean getIsrun() {
		return false;
	}

	@Override
	public boolean getIsQueue() {
		return true;
	}
    
    
    
    
  

}
