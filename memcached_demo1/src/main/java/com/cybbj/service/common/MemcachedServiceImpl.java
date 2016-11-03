/**   
 * 类名：MemcachedServiceImpl
 *
 */
package com.cybbj.service.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whalin.MemCached.MemCachedClient;

/** 
 * MemcachedServiceImpl: Memcached实现类
 * 
 * @version 1.0
 * @author 15989
 * @modified 2016-11-1 v1.0 15989 新建 
 */
@Service(value="memcachedService")
public class MemcachedServiceImpl implements MemcachedService {
	
	private static final Log log = LogFactory.getLog(MemcachedServiceImpl.class);
	private static final long serialVersionUID= 1;

	@Autowired
	private MemCachedClient memCachedClient;	
	

	/** (非 Javadoc) 
	 * @param key
	 * @return 
	 * @see com.cybbj.service.common.MemcachedService#delete(java.lang.String) 
	 */
	public boolean delete(String key) {
		return memCachedClient.delete(key);
	}

	/** (非 Javadoc) 
	 * @param Key
	 * @param object 
	 * @see com.cybbj.service.common.MemcachedService#set(java.lang.String, java.lang.Object) 
	 */
	public void set(String key, Object object) {
		if (object != null) {
			memCachedClient.set(key, object);
		} else {
			memCachedClient.delete(key);
		}
	}

	/** (非 Javadoc) 
	 * @param key
	 * @return 
	 * @see com.cybbj.service.common.MemcachedService#get(java.lang.String) 
	 */
	public Object get(String key) {
		return memCachedClient.get(key);
	}

	/** (非 Javadoc) 
	 * @param key
	 * @param clazz
	 * @return 
	 * @see com.cybbj.service.common.MemcachedService#deleteWithType(java.lang.String, java.lang.Class) 
	 */
	public boolean deleteWithType(String key, Class clazz) {
		return memCachedClient.delete(getKeyWithType(key, clazz));
	}

	/** (非 Javadoc) 
	 * @param key
	 * @param object 
	 * @see com.cybbj.service.common.MemcachedService#setWithType(java.lang.String, java.lang.Object) 
	 */
	public void setWithType(String key, Object object) {
		memCachedClient.set(getKeyWithType(key, object.getClass()), object);
	}

	/** (非 Javadoc) 
	 * @param key
	 * @param object
	 * @param clazz 
	 * @see com.cybbj.service.common.MemcachedService#setWithType(java.lang.String, java.lang.Object, java.lang.Class) 
	 */
	public void setWithType(String key, Object object, Class clazz) {
		if (object != null) {
			memCachedClient.set(getKeyWithType(key, clazz), object);
		} else {
			memCachedClient.delete(getKeyWithType(key, clazz));
		}
	}

	/** (非 Javadoc) 
	 * @param key
	 * @param clazz
	 * @return 
	 * @see com.cybbj.service.common.MemcachedService#getWithType(java.lang.String, java.lang.Class) 
	 */
	public Object getWithType(String key, Class clazz) {
		return memCachedClient.get(getKeyWithType(key, clazz));
	}

	/** (非 Javadoc) 
	 * @param keys
	 * @param clazz
	 * @return 
	 * @see com.cybbj.service.common.MemcachedService#getWithType(java.lang.String[], java.lang.Class) 
	 */
	public Object[] getWithType(String[] keys, Class clazz) {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = getKeyWithType(keys[i], clazz);
		}
		try {
			return memCachedClient.getMultiArray(keys);
		} catch (Exception e) {
			log.error(e);
		}
		return new Object[0];
	}

	/** (非 Javadoc) 
	 * @param keys
	 * @param clazz
	 * @param objects 
	 * @see com.cybbj.service.common.MemcachedService#setWithType(java.lang.String[], java.lang.Class, java.lang.Object[]) 
	 */
	public void setWithType(String[] keys, Class clazz, Object[] objects) {
		for (int i = 0; i < keys.length; i++) {
			memCachedClient.set(getKeyWithType(keys[i], clazz), objects[i]);
		}
	}
	
	protected String getKeyWithType(String key,Class clazz) {
		return clazz.getSimpleName() + "-" + key;
	}

	
}
