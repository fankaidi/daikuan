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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.kensure.mem.CollectionUtils;

public class TestService {


	public static long insertPeople() throws IOException {
		long start = System.currentTimeMillis();
		return System.currentTimeMillis() - start;
	}

	public static long insertThreadPeople() {
		long start = System.currentTimeMillis();
		// 定义一个缓冲的线程值 线程池的大小根据任务变化
		ExecutorService threadPool = Executors.newCachedThreadPool();

		int MAX = 100000;
		Map<String, String> timemap = new HashMap<String, String>();
		List<String> keylist = new ArrayList<String>();
		for (int index = 0; index < MAX; index++) {
			String firstKey = start + "" + index;
			keylist.add(firstKey);
			if (index > 0 && index % 10000 == 0) {
				TestThread tt = new TestThread(keylist, timemap);
				tt.start();
				keylist = new ArrayList<String>();
			}

		}
		if (CollectionUtils.isNotEmpty(keylist)) {
			TestThread tt = new TestThread(keylist, timemap);
			tt.start();
		}

		try {
			int run = 1000;
			while (run>0) {
				Collection<String> list = timemap.values();
				if (!list.contains("0")) {
					break;
				}
				Thread.sleep(5000);
				run--;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("主线执行。");
		return System.currentTimeMillis() - start;
	}

	public static void main(String args[]){
		Map<String, String> timemap = new HashMap<String, String>();
		for (int index = 0; index < 10; index++) {
			List<String> keylist = new ArrayList<String>();
			TestThread tt = new TestThread(keylist, timemap);
			tt.start();

		}
		
		try {
			int run = 1000;
			while (run>0) {
				Collection<String> list = timemap.values();
				if (!list.contains("0")) {
					break;
				}
				Thread.sleep(5000);
				run--;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("主线执行。");
	}
	
	public static boolean tableexists() throws IOException {
		boolean exists = false;
		return exists;
	}

}
