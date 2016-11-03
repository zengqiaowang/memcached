/**   
 * 类名：MemcachedTest
 *
 */
package com.cybbj;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * MemcachedTest: TODO请填写类描述
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-10-27 v1.0 15989 新建
 */
public class MemcachedTest {
	protected static MemCachedClient mcc = new MemCachedClient();

	protected static MemcachedTest memcachedTest = new MemcachedTest();

	// 设置与memcached服务器的连接信息
	static {
		// / 服务器列表和其权重
		String[] servers = {"127.0.0.1:11210"};
		Integer[] weights = {5};

		// 获取sock连接池的实例对象
		SockIOPool pool = SockIOPool.getInstance();

		pool.setServers(servers);
		pool.setWeights(weights);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(100);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// 设置主线程的睡眠时间
		pool.setMaintSleep(30);

		// 设置TCP的参数，连接超时等
		pool.setNagle(false);
		pool.setSocketTO(3000);
		pool.setSocketConnectTO(0);

		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);

		// 初始化连接池
		pool.initialize();

	}

	/**
	 * 保护型构造方法，不允许实例化！
	 * 
	 */
	protected MemcachedTest() {

	}

	/**
	 * 获取唯一实例.
	 * 
	 * @return
	 */
	public static MemcachedTest getInstance() {
		return memcachedTest;
	}

	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, String value) {
		return mcc.add(key, value);
	}
	
	public boolean addObj(String key,Object obj) {
		return mcc.add(key, obj);
	}

	public boolean add(String key, String value, Date expiry) {
		return mcc.add(key, value, expiry);
	}

	public boolean replace(String key, String value) {
		return mcc.replace(key, value);
	}

	public boolean replace(String key, String value, Date expiry) {
		return mcc.replace(key, value, expiry);
	}

	public Object get(String key) {
		return mcc.get(key);
	}

	public Object flush() {
		return mcc.flushAll();
	}
	
	public boolean delete(String key) {
		return mcc.delete(key);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemcachedTest mt = MemcachedTest.getInstance();
		mt.flush();
		for (int i = 0; i < 10; i++) {
			mt.add("hello" + i, "world" + i);
		}
		Map<String, String> oMap = new HashMap<String, String>();
		oMap.put("param1", "val1");
		oMap.put("param2", "val2");
		oMap.put("param3", "val3");
		mt.addObj("testMap", oMap);
		for (int i = 0; i < 10; i++) {
			System.out.println("the value of key Hello" + i + " is: " + mt.get("hello" + i));
		}
		System.out.println(mt.get("testMap"));
	}
}
