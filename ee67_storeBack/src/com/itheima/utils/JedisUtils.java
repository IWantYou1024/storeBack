package com.itheima.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
	//1.需要准备连接池
	private static JedisPool pool = null;
	
	static{
		try {
			//初始化连接池
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			
			//加载配置文件
			/*//2.类加载器加载文件  可以加载任意文件
			InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
			//1.创建properties对象 使用加载
			//properties等号前后的空格是可以忽略
			//在value值中不可以写#号 如果真的要使用 转义
			Properties properties = new Properties();
			properties.load(is);
			System.out.println(properties.getProperty("jedis.host"));*/
			
			
			//加载properties 快速开发中常用  只能加载properties配置文件  后缀名不需要加properties
			ResourceBundle bundle = ResourceBundle.getBundle("jedis");
			System.out.println(bundle.getString("jedis.host"));
			
			String host = bundle.getString("jedis.host");
			int port = Integer.valueOf(bundle.getString("jedis.port")); 
			int minIdle =  Integer.valueOf(bundle.getString("jedis.minIdle"));
			int maxIdle =  Integer.valueOf(bundle.getString("jedis.maxIdle"));
			
			
			//配置最小空闲数
			poolConfig.setMinIdle(minIdle);
			//配置最大空闲数
			poolConfig.setMaxIdle(maxIdle);
			
			//连接池对象
			pool = new JedisPool(poolConfig, host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Jedis jedis = JedisUtils.getJedis();
		System.out.println(jedis.get("username"));
		JedisUtils.closeJedis(jedis);
	}
	
	
	//2.对外提供获得连接的方法  返回值是jedis对象
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	
	//3.对外提供关闭jedis的方法
	public static void closeJedis(Jedis jedis){
		jedis.close();
	}
	
}
