package com.chance.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chance.dao.AdminDao;
import com.chance.domain.Admin;
import com.chance.service.AdminService;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
	private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public void changepwd(Integer adminid, String oldpwd, String newpwd)
			throws BusinessException {
		Admin admin = adminDao.get(adminid);
		if(!DigestUtils.md5Hex(oldpwd.trim()).equals(admin.getPassword())){
			throw new BusinessException("原始密码错误");
		} else{
			admin.setPassword(DigestUtils.md5Hex(newpwd.trim()));
			adminDao.update(admin);
		}
	}
	@Override
	public Admin login(Admin admin) throws BusinessException {
		Assert.notNull(admin, "admin不能为空");
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("loginName", admin.getLoginName());
		params.put("password",DigestUtils.md5Hex(admin.getPassword()));
		
		Admin resultAdmin = adminDao.get(params);
		if(resultAdmin != null){
			return resultAdmin;
		}else {
			throw new BusinessException("用户名或密码错误");
		}
	}
	
	@Override
	public List<Admin> getAll() throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.getAll();
	}
	@Override
	public void add(Admin admin) throws BusinessException {
		Assert.notNull(admin, "admin不能为空");
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		admin.setCreateTime(System.currentTimeMillis());
		admin.setIsLocked(false);
		adminDao.save(admin);
		
	}
	@Override
	public void update(Admin entity) throws BusinessException {
		adminDao.update(entity);
	}
	@Override
	public void resetPwd(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void lock(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void irreversibleDelete(Integer id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Admin getById(int id) throws BusinessException {
		return adminDao.get(id);
	}
	
	
}
