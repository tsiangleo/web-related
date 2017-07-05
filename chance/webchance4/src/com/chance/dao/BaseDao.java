package com.chance.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.chance.dao.impl.DBRuntimeException;
import com.chance.dao.impl.ResultNotUniqueException;
import com.chance.domain.Pager;

/**
 *
 * @param <T> 业务实体对象
 * @param <PK>
 */

public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 根据ID获取实体对象.没找到则返回null。不会延迟加载
	 * 
	 * select * from T where id=?
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);
	
	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * select * from T where id in(?)
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<T> get(PK[] ids);
	
	/**
	 * 根据属�?�名和属性�?�获取实体对�?.没找到则返回null。找到不止一条则抛异常。
	 * 
	 * select * from T where propertyName=value
	 * 
	 * @param propertyName
	 *            属�?�名�?
	 * @param value
	 *            属�?��??
	 * @return 实体对象
	 */
	@Deprecated
	public T get(String propertyName, Object value) throws ResultNotUniqueException;


	/**
	 * 根据属�?�名和属性�?�获取实体对�?.找到则返回null。找到不止一条则抛异常。
	 * 
	 * select * from T where propertyName1=value1,propertyName2=value2,...
	 * 
	 * @return
	 */
	@Deprecated
	public T get(Map<String, Object> params) throws ResultNotUniqueException;
	
	/**
	 * 根据属�?�名和属性�?�获取实体对象集�?.
	 * 
	 * @param propertyName
	 *            属�?�名�?
	 * @param value
	 *            属�?��??
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * 2015�?6�?27�? 星期�? 新增
	 * sql="select * from T where propertyName1=value1,propertyName2=value2,...
	 * @return
	 */
	public List<T> getList(Map<String, Object> params);
	
	/**
	 * 获取�?有实体对象集�?.
	 * 
	 * select * from T
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();
	
	/**
	 * 获取�?有实体对象�?�数.
	 * 
	 * @return 实体对象总数
	 */
	public Integer getTotalCount();
	
	/**
	 * 获取�?有实体对象�?�数.
	 * 
	 * @return 实体对象总数
	 */
	public Integer getTotalCount(String propertyName, Object value);

	/**
	 * 根据属�?�名、修改前后属性�?�判断在数据库中是否唯一(若新修改的�?�与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属�?�名�?
	 * @param oldValue
	 *            修改前的属�?��??
	 * @param oldValue
	 *            修改后的属�?��??
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue, Object newValue);
	
	/**
	 * 根据属�?�名判断数据是否已存�?.
	 * 
	 * @param propertyName
	 *            属�?�名�?
	 * @param value
	 *            �?
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void save(T entity);
	
	/**
	 * 保存实体对象.
	 * 
	 * @param entityList
	 *            对象
	 */
	public void save(List<T> entityList);

	/**
	 * 更新实体对象.(更新除id以外的所有属�?)
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);
	
//	/**
//	 * 删除实体对象.
//	 * 
//	 * @param entity
//	 *            对象
//	 * @return
//	 */
//	public void delete(T entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * delete from T where id = ?
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);


	/**
	 * 根据Pager对象进行查询(提供分页、查找�?�排序功�?).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Pager<T> getByPager(Pager<T> pager);
	

	
	/**
	 * 更新数据
	 * @param property 要更新的属�?�（属�?�名-新�?�）
	 * @param identity 更新条件（属性名-值Map�?
	 */
	public void update(Map<String, Object> property, Map<String, Object> identity);
	
	/**
	 * String hqlUpdate = "UPDATE FeedBack " +
					"SET C_FeedBack_CheckResult= "+checkResult+" ," +
					" C_FeedBack_CheckAdminID = "+checkAdminID+" , " +
					"C_FeedBack_CheckTime = '"+TypeTransfer.Timestamp2String(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss")+"' WHERE C_FeedBack_ID = "+checkList.get(i)+"";
					
//	 * 更新数据
//	 * @param fields 字段-值Map
//	 * @param where 更新条件,�?"a='1' AND b='2'"
//	 * 
//	 * 
//	 */
//	@Deprecated
//	public void update(Map fields, String where);
	
//	/**
//	 * 更新数据
//	 * @param po 要更新的对象，保证对象的属�?�名和字段名对应
//	 * @param where 更新条件(字段-值Map)
//	 */
//	public void update(T po, Map where);
//	
//	/**
//	 * 更新数据
//	 * @param po 要更新的对象，保证对象的属�?�名和字段名对应
//	 * @param where 更新条件,�?"a='1' AND b='2'"
//	 */
//	public void update( T po, String where) ;
	
//	/**
//	 * 新增数据
//	 * @param table  表名
//	 * @param fields 字段-值Map
//	 */
//	public void insert(String table, Map fields);
//		
//	/**
//	 * 新增数据
//	 * @param table 表名
//	 * @param po 要新增的对象，保证对象的属�?�名和字段名对应
//	 */
//	public void insert(String table, Object po);
	
	
}