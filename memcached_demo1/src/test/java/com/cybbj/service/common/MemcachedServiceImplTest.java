/**   
 * 类名：MemcachedServiceImplTest
 *
 */
package com.cybbj.service.common;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cybbj.pojo.UserBean;

import junit.framework.TestCase;

/** 
 * MemcachedServiceImplTest: TODO请填写类描述
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-11-1 v1.0 15989 新建 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MemcachedServiceImplTest extends TestCase{

	@Autowired
	@Qualifier(value="memcachedService")
	private MemcachedService memcachedService;
	
	@Test
	public void testSet() {
		try {
			//开始设值
			memcachedService.set("name", " string ");
			memcachedService.set("int", 5);
			memcachedService.set("double", 5.5);
			
			UserBean userBean = new UserBean();
			userBean.setAge(21);
			userBean.setName("名字");
			
			memcachedService.set("UserBean", userBean);
			
			List<UserBean> data = new ArrayList<UserBean>();
			for (int i = 0; i < 3; i++) {
				UserBean tempBean = new UserBean();
				tempBean.setAge(i);
				tempBean.setName("test_"+i);
				data.add(tempBean);
			}
			memcachedService.set("data", data);
			memcachedService.setWithType("data2", data);
			
			//开始取值
			String name = (String) memcachedService.get("name");
			int i = (Integer) memcachedService.get("int");
			double d = (Double) memcachedService.get("double");
			UserBean b = (UserBean) memcachedService.get("UserBean");
			data = (List<UserBean>)memcachedService.get("data");
			List<UserBean> data2 = (List<UserBean>) memcachedService.getWithType("data2", data.getClass());
			
			System.out.println("字符串：" + name);
			System.out.println("整数型：" + i);
			System.out.println("双精度：" + d);
			System.out.println("UserBean toString: " + b.toString());
			System.out.println("data toString: " + data.toString());
			System.out.println("data2 toString: " + data2.toString());
			System.out.println("本机testMap:"+memcachedService.get("testMap"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
