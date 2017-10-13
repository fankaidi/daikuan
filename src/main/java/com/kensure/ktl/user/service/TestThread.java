/*
 * 文件名称: UserLoginServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-5
 * 修改内容: 
 */
package com.kensure.ktl.user.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestThread extends Thread {


	private List<String> list;
	
	private Map<String,String> timemap = new HashMap<String,String>();
	
	
	public TestThread(List<String> keylist,Map map){
		this.list = keylist;
		this.timemap = map;
	}
	
	
	
	public static long insertPeople(List<String> keylist) throws IOException {
		long start = System.currentTimeMillis();
	
		return System.currentTimeMillis() - start;
	}

	
	@Override
	public void run() {
		try {
			this.timemap.put(""+Thread.currentThread().getId(), "0");
			long ped = insertPeople(this.list);
			this.timemap.put(""+Thread.currentThread().getId(), "1");
			System.out.println("子线程" + Thread.currentThread().getId()+ "执行完毕,时间==="+ped+"==\t");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			this.timemap.put(""+Thread.currentThread().getId(), "1");
		}
		
	}

}
