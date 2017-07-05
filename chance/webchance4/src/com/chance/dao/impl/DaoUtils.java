package com.chance.dao.impl;

import java.util.Map;

import org.springframework.util.Assert;

public class DaoUtils {

	/**
	 * 生成SQL子句，类似如下字符串 ：<br>
	 * "key1 = :key1 , key2 = :key2 , key3 = :key3" 或者<br>
	 * "key1 = :key1 and key2 = :key2 and key3 = :key3"
	 * @param params
	 * @param seperator 分隔符，如"and"或者","
	 * @param propertyMapper 对象属性到数据库列的映射
	 * @return
	 */
	public static String getSqlClause(Map<String, Object> params ,String seperator,Map<String, String> propertyMapper) {
		Assert.notEmpty(params, "map不能为空");
		Assert.hasText(seperator, "seperator不能为空");
		Assert.notEmpty(propertyMapper, "map不能为空");
		
		String seperatorString = " "+seperator+" ";
		StringBuffer sb = new StringBuffer();
		for(String key : params.keySet()){
			String filedName = propertyMapper.get(key);
			sb.append(filedName +" = :"+filedName+seperatorString);
		}
		
		String sufix = sb.toString();
		sufix = sufix.substring(0,sufix.length()-seperatorString.length());
		return sufix;
	}
	
	/**
	 * 生成SQL子句，类似如下字符串 ：<br>
	 * "key1 like '% :key1 %' and key2 like '% :key2 %' and key3 like '% :key3 %' 
	 * @param params
	 * @param seperator 分隔符，"and"
	 * @param propertyMapper 对象属性到数据库列的映射
	 * @return
	 */
	public static String getSqlLikeClause(Map<String, Object> params ,String seperator,Map<String, String> propertyMapper) {
		Assert.notEmpty(params, "map不能为空");
		Assert.hasText(seperator, "seperator不能为空");
		Assert.notEmpty(propertyMapper, "map不能为空");
		
		String seperatorString = " "+seperator+" ";
		StringBuffer sb = new StringBuffer();
		for(String key : params.keySet()){
			String filedName = propertyMapper.get(key);
			sb.append(filedName +" like '% :"+filedName+" %'"+seperatorString);
		}
		
		String sufix = sb.toString();
		sufix = sufix.substring(0,sufix.length()-seperatorString.length());
		return sufix;
	}
}
