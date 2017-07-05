package com.chance.dao;

import java.util.List;
import java.util.Map;

import com.chance.dao.impl.ResultNotUniqueException;
import com.chance.domain.Pager;
import com.chance.domain.ReportChatMsg;
import com.chance.domain.ReportUser;

public interface ReportUserDao{
	/**
	 * 根据ID获取实体对象.没找到则返回null。不会延迟加载
	 * @param id ReportUser在本地数据库中的主键
	 * @param isNeedChatMsg 是否同时返回对应的ReportChatMsg
	 * @return
	 */
	public ReportUser get(Integer id, boolean isNeedChatMsg);
	
	/**
	 * 返回某条ReportUser记录对应的ReportChatMsg
	 * @param id 某条ReportUser记录的id
	 * @return
	 */
	public List<ReportChatMsg> getChatMsg(Integer ReportUserid);
	
//	/**
//	 * 根据ID数组获取实体对象集合.
//	 * 
//	 * select * from T where id in(?)
//	 * 
//	 * @param ids
//	 *            ID对象数组
//	 * 
//	 * @return 实体对象集合
//	 */
//	public List<ReportUser> get(Integer[] ids);
	
//	/**
//	 * 根据属�?�名和属性�?�获取实体对�?.没找到则返回null。找到不止一条则抛异常。
//	 * 
//	 * select * from T where propertyName=value
//	 * 
//	 * @param propertyName
//	 *            属�?�名�?
//	 * @param value
//	 *            属�?��??
//	 * @return 实体对象
//	 */
//	@Deprecated
//	public ReportUser get(String propertyName, Object value) throws ResultNotUniqueException;
//
//
//	/**
//	 * 根据属�?�名和属性�?�获取实体对�?.找到则返回null。找到不止一条则抛异常。
//	 * 
//	 * select * from T where propertyName1=value1,propertyName2=value2,...
//	 * 
//	 * @return
//	 */
//	@Deprecated
//	public ReportUser get(Map<String, Object> params) throws ResultNotUniqueException;
	
//	/**
//	 * 根据属�?�名和属性�?�获取实体对象集�?.
//	 * 
//	 * @param propertyName
//	 *            属�?�名�?
//	 * @param value
//	 *            属�?��??
//	 * @return 实体对象集合
//	 */
//	public List<ReportUser> getList(String propertyName, Object value);
//
	/**
	 * 2015�?6�?27�? 星期�? 新增
	 * sql="select * from T where propertyName1=value1,propertyName2=value2,...
	 * @return
	 */
	public List<ReportUser> getList(Map<String, Object> params);
//	
//	/**
//	 * 获取�?有实体对象集�?.
//	 * 
//	 * select * from T
//	 * 
//	 * @return 实体对象集合
//	 */
//	public List<ReportUser> getAll();
//	
//	/**
//	 * 获取�?有实体对象�?�数.
//	 * 
//	 * @return 实体对象总数
//	 */
//	public Integer getTotalCount();
	
//	/**
//	 * 获取�?有实体对象�?�数.
//	 * 
//	 * @return 实体对象总数
//	 */
//	public Integer getTotalCount(String propertyName, Object value);
//
//	/**
//	 * 根据属�?�名、修改前后属性�?�判断在数据库中是否唯一(若新修改的�?�与原来值相等则直接返回true).
//	 * 
//	 * @param propertyName
//	 *            属�?�名�?
//	 * @param oldValue
//	 *            修改前的属�?��??
//	 * @param oldValue
//	 *            修改后的属�?��??
//	 * @return boolean
//	 */
//	public boolean isUnique(String propertyName, Object oldValue, Object newValue);
	
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
	 * 保存ReportUser及其对应的ReportChatMsg
	 * @param entity
	 */
	public void save(ReportUser entity);

	/**
	 * 批量保存ReportUser
	 * @param entityList
	 */
	public void save(List<ReportUser> entityList);

//	/**
//	 * 更新实体对象.(更新除id以外的所有属�?)
//	 * 
//	 * @param entity
//	 *            对象
//	 */
//	public void update(ReportUser entity);
	
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
	public void delete(Integer id);
//
//	/**
//	 * 根据ID数组删除实体对象.
//	 * 
//	 * @param ids
//	 *            ID数组
//	 */
//	public void delete(Integer[] ids);
	
	/**
	 * 分页查询ReportUser,该方法不返回每条ReportUser记录对应的ReportChatMSg
	 * @param pager Pager对象
	 * @return
	 */
	public Pager<ReportUser> getByPager(Pager<ReportUser> pager);
	

	
	/**
	 * 更新数据
	 * @param property 要更新的属�?�（属�?�名-新�?�）
	 * @param identity 更新条件（属性名-值Map�?
	 */
	public void update(Map<String, Object> property, Map<String, Object> identity);
	
}
