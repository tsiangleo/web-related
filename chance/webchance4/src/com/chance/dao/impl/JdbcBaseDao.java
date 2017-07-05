package com.chance.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.chance.dao.BaseDao;
import com.chance.domain.Pager;

@Repository
public abstract class JdbcBaseDao<T, PK extends Serializable> implements BaseDao<T, PK> {

	protected static final Logger logger = LoggerFactory.getLogger(JdbcBaseDao.class);
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public JdbcBaseDao() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}
	

	/**
	 * 生成SQL子句，类似如下字符串 ：<br>
	 * "key1 = :key1 , key2 = :key2 , key3 = :key3" 或者<br>
	 * "key1 = :key1 and key2 = :key2 and key3 = :key3"
	 * @param params
	 * @param seperator 分隔符，如"and"或者","
	 * @param propertyMapper 对象属性到数据库列的映射
	 * @return
	 */
	protected String getSqlClause(Map<String, Object> params ,String seperator,Map<String, String> propertyMapper) {
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
	protected String getSqlLikeClause(Map<String, Object> params ,String seperator,Map<String, String> propertyMapper) {
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
	
	@Override
	public abstract T get(PK id) throws DBRuntimeException;

	@Override
	public abstract List<T> get(PK[] ids);

	@Override
	public abstract T get(String propertyName, Object value);

	@Override
	public abstract List<T> getList(String propertyName, Object value);

	@Override
	public abstract List<T> getAll();

	@Override
	public Integer getTotalCount() {
		String sql = "select count(*) from " + entityClass.getName();
		return jdbcTemplate.queryForInt(sql);
	}

	@Override
	public boolean isUnique(String propertyName, Object oldValue,
			Object newValue) {
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(newValue, "属性值value不能为空");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
//		Assert.hasText(propertyName, "属性名propertyName不能为空");
//		Assert.notNull(value, "属性值value不能为空");
//		T object = get(propertyName, value);
//		return (object != null);
		
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(value, "属性值value不能为空");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.getList(map).size() > 0 ? true: false; //本地数据有重复的,所有如此实现
	}

	@Override
	public abstract void save(T entity);

	@Override
	public abstract void update(T entity);
	
	@Override
	public abstract Pager<T> getByPager(Pager<T> pager);

}