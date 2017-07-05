package com.chance.service;

import java.util.List;

import com.chance.domain.Admin;
import com.chance.service.impl.BusinessException;

public interface AdminService {
	
	/**
	 * 修改密码
	 * @param adminid 管理员id
	 * @param oldpwd 原始密码（未加密）
	 * @param newpwd 新密码（未加密）
	 * @throws BusinessException
	 */
	void changepwd(Integer adminid, String oldpwd,String newpwd) throws BusinessException;
	

	/**
	 * 登陆
	 * 成功则返回Admin，否则抛异常。
	 * @param admin
	 * @return
	 * @throws BusinessException
	 */
	Admin login(Admin admin)  throws BusinessException;
	
	/**
	 * 查看所有管理员信息（包括被锁定的）
	 * @return
	 * @throws BusinessException
	 */
	List<Admin> getAll()throws BusinessException;
	
	/**
	 * 添加管理员
	 * @param admin
	 * @throws BusinessException
	 */
	void add(Admin admin)throws BusinessException;
	

	/**
	 * 修改管理员信息
	 * @param entity
	 * @throws BusinessException
	 */
	void update(Admin entity)throws BusinessException;
	
	/**
	 * 重置密码
	 * @param id
	 * @throws BusinessException
	 */
	void resetPwd(Integer id)throws BusinessException;
	
	/**
	 * 锁定管理员账号
	 * @param id
	 * @throws BusinessException
	 */
	void lock(Integer id)throws BusinessException;
	
	/**
	 * 彻底删除管理员账号
	 * @param id
	 * @throws BusinessException
	 */
	void irreversibleDelete(Integer id)throws BusinessException;
	
	/**
	 * 按id查询管理员
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	Admin getById(int id)throws BusinessException;
}
