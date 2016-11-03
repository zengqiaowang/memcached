/**   
 * 类名：MemcachedService
 *
 */
package com.cybbj.service.common;

/** 
 * MemcachedService: Memcached 接口
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-11-1 v1.0 15989 新建 
 */
public interface MemcachedService {
	public boolean delete(String key);
	public void set(String key,Object object);
	public Object get(String key);
	public boolean deleteWithType(String key,Class clazz);
	public void setWithType(String key,Object object);
	public void setWithType(String key,Object object,Class clazz);
	public Object getWithType(String key,Class clazz);
	public Object[] getWithType(String keys[],Class clazz);
	public void setWithType(String keys[],Class clazz,Object objects[]);
}
