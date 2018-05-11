package com.kensure.ktl.liuliang.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.kensure.mem.DateUtils;
import co.kensure.time.MyTimerTasker;

import com.kensure.ktl.liuliang.service.LLBaseInfoService;


/**
 * 流量定时器，每4天更新一下流量，将4天之前导入的流量作废，同时在间隔3天之后再卖给他。
 * @author fankd created on 2018-3-19
 * @since 
 */
@Service
public class LLBaseInfoTimerService implements MyTimerTasker{
	
	private final static Logger LOGGER = Logger.getLogger(LLBaseInfoTimerService.class);

	@Resource
	private LLBaseInfoService llBaseInfoService;
	
	@Override
	public void run() {
		try {
			llBaseInfoService.expireFirstBaseInfo();
			llBaseInfoService.expireSecondBaseInfo();
			llBaseInfoService.activeFirstBaseInfo();		
		} catch (Throwable e) {
			LOGGER.error("定时器LLBaseInfoTimerService异常",e);
			e.printStackTrace();
		} finally {
		
		}
	}
	
	
	@Override
	public Date getFirstTime() {
		Date day = new Date();
		//明天的 00:00:00 执行这个任务
		Date tom = DateUtils.getPastDay(day, 1);
		String tomstr = DateUtils.formatDateStart(tom);
		Date tomstart = DateUtils.parse(tomstr, DateUtils.DATE_FORMAT_PATTERN);
		return tomstart;
	}

	@Override
	public long getPeriod() {
		return 1000 * 60 * 60 * 24;
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
