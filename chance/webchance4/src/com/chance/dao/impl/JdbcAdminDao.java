package com.chance.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.chance.dao.AdminDao;
import com.chance.domain.Admin;
import com.chance.domain.Pager;


@Repository("adminDao")
public class JdbcAdminDao extends JdbcBaseDao<Admin, Integer> implements AdminDao{

	/**
	 * 对象属性→数据库表字段的映射
	 */
	private static final Map<String, String> propertyMapper = new HashMap<String, String>();
	static{
		propertyMapper.put("id", "C_Admin_ID");
		propertyMapper.put("loginName", "C_Admin_LoginName");
		propertyMapper.put("password", "C_Admin_Pwd");
		propertyMapper.put("name", "C_Admin_Name");
		propertyMapper.put("level", "C_Admin_Level");
		propertyMapper.put("isLocked", "C_Admin_IsLocked");
		propertyMapper.put("createTime", "C_Admin_CreateTime");
	}

	private static final class EntityMapper implements RowMapper<Admin> {
	    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Admin result = new Admin();
	    	result.setCreateTime(rs.getLong("C_Admin_CreateTime"));
	    	result.setId(rs.getInt("C_Admin_ID"));
	    	result.setIsLocked(rs.getBoolean("C_Admin_IsLocked"));
	    	result.setLevel(rs.getInt("C_Admin_Level"));
	    	result.setLoginName(rs.getString("C_Admin_LoginName"));
	    	result.setName(rs.getString("C_Admin_Name"));
	    	result.setPassword(rs.getString("C_Admin_Pwd"));
            return result;
	    }
	}
	@Override
	public Admin get(Map<String, Object> params)
			throws ResultNotUniqueException {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_Admin where ";
		String sql = prefix + getSqlClause(params,"and",propertyMapper);
		logger.info(sql);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(String key : params.keySet()){
			String filedName = propertyMapper.get(key);
			paramMap.put(filedName, params.get(key));
		}
		
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new EntityMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (IncorrectResultSizeDataAccessException e) {
			throw new ResultNotUniqueException(sql);
		}
	}

	@Override
	public List<Admin> getList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(List<Admin> entityList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Map<String, Object> property,
			Map<String, Object> identity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin get(Integer id) throws DBRuntimeException {
		Assert.notNull(id, "id 不能为空");
		String sql = "select * from C_Admin where C_Admin_ID = ?";
		logger.info(sql);
		try {
			return this.jdbcTemplate.queryForObject(sql,new Object[]{id},new EntityMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (IncorrectResultSizeDataAccessException e) {
			throw new ResultNotUniqueException(e.getMessage());
		}
	}

	@Override
	public List<Admin> get(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin get(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getList(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAll() {
		String sql = "select * from C_Admin order by C_Admin_CreateTime desc";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.query(sql, new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void save(Admin entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "insert into C_Admin(C_Admin_LoginName,"
				+ " C_Admin_Pwd, C_Admin_Name,"
				+ " C_Admin_Level, C_Admin_IsLocked,"
				+ " C_Admin_CreateTime) "
				+ "values (?, ?, ?, ?, ?, ?)";
		logger.info(sql);
		
		try {
			this.jdbcTemplate.update(sql,entity.getLoginName(), entity.getPassword(),entity.getName(),
					entity.getLevel(), entity.getIsLocked(),entity.getCreateTime());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	@Override
	public void update(Admin entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "update C_Admin set C_Admin_LoginName=?,"
				+ " C_Admin_Pwd=?, C_Admin_Name=?,"
				+ " C_Admin_Level=?, C_Admin_IsLocked=?,"
				+ " C_Admin_CreateTime=? where C_Admin_ID = ?";
		logger.info(sql);
		
		try {
			this.jdbcTemplate.update(sql,entity.getLoginName(), entity.getPassword(),
					entity.getName(),entity.getLevel(), entity.getIsLocked(),
					entity.getCreateTime(),entity.getId());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	@Override
	public Pager<Admin> getByPager(Pager<Admin> pager) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
