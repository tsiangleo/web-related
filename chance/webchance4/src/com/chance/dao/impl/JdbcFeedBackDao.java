package com.chance.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.chance.dao.FeedBackDao;
import com.chance.domain.FeedBack;
import com.chance.domain.Pager;
import com.chance.domain.Pager.OrderType;


@Repository("feedBackDao")
public class JdbcFeedBackDao extends JdbcBaseDao<FeedBack, Integer> implements FeedBackDao{
	
	/**
	 * 对象属性→数据库表字段的映射
	 */
	private static final Map<String, String> propertyMapper = new HashMap<String, String>();
	static{
		propertyMapper.put("id", "C_FeedBack_ID");
		propertyMapper.put("userId", "C_FeedBack_UserID");
		propertyMapper.put("userName", "C_FeedBack_UserName");
		propertyMapper.put("content", "C_FeedBack_Content");
		propertyMapper.put("time", "C_FeedBack_Time");
		propertyMapper.put("checkResult", "C_FeedBack_CheckResult");
		propertyMapper.put("checkAdminId", "C_FeedBack_CheckAdminID");
		propertyMapper.put("checkTime", "C_FeedBack_CheckTime");
	}

	private static final class EntityMapper implements RowMapper<FeedBack> {
	    public FeedBack mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	FeedBack reportDiary = new FeedBack();
	    	reportDiary.setContent(rs.getString("C_FeedBack_Content"));
	    	reportDiary.setCheckAdminId(rs.getInt("C_FeedBack_CheckAdminID"));
	    	reportDiary.setCheckResult(rs.getInt("C_FeedBack_CheckResult"));
	    	reportDiary.setCheckTime(rs.getLong("C_FeedBack_CheckTime"));
	    	reportDiary.setId(rs.getInt("C_FeedBack_ID"));
	    	reportDiary.setTime(rs.getLong("C_FeedBack_Time"));
	    	reportDiary.setUserId(rs.getInt("C_FeedBack_UserID"));
	    	reportDiary.setUserName(rs.getString("C_FeedBack_UserName"));
            return reportDiary;
	    }
	}
	

	@Override
	public FeedBack get(Integer id) {
		Assert.notNull(id, "id 不能为空");
		String sql = "select * from C_FeedBack where C_FeedBack_ID = ?";
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
	public List<FeedBack> get(Integer[] ids) {
		Assert.notEmpty(ids, "ids 不能为空");
		// 生成字符串："(id1,id2,id3)"
		StringBuilder sb =  new StringBuilder("(");
		for (int i = 0; i < ids.length; i++) {
			sb.append(ids[i]);
			if(i+1 < ids.length)
				sb.append(",");
			else 
				sb.append(")");
			
		}
		
		String sql = "select * from C_FeedBack where C_FeedBack_ID in "+sb.toString() + " order by C_FeedBack_Time desc";
		logger.info(sql);
		try {
			return this.jdbcTemplate.query(sql,new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public FeedBack get(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		Assert.notNull(value, "属性值不能为空");
		String sql = "select * from C_FeedBack where "+propertyMapper.get(propertyName)+" = ?";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.queryForObject(sql,new Object[]{value},new EntityMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}catch (IncorrectResultSizeDataAccessException e) {
			throw new ResultNotUniqueException(sql);
		}
	}

	@Override
	public List<FeedBack> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(value, "属性值value不能为空");
		String sql = "select * from C_FeedBack where "+propertyMapper.get(propertyName)+" = ? order by C_FeedBack_Time desc";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.query(sql, new Object[]{value},new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}
	
	@Override
	public FeedBack get(Map<String, Object> params) {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_FeedBack where ";
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
	public List<FeedBack> getList(Map<String, Object> params) {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_FeedBack where ";
		String sql = prefix + getSqlClause(params,"and",propertyMapper) + " order by C_FeedBack_Time desc";
		logger.info(sql);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(String key : params.keySet()){
			String filedName = propertyMapper.get(key);
			paramMap.put(filedName, params.get(key));
		}
		
		try {
			return namedParameterJdbcTemplate.query(sql, paramMap, new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public List<FeedBack> getAll() {	
		String sql = "select * from C_FeedBack order by C_FeedBack_Time desc";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.query(sql, new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void save(FeedBack entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "insert into C_FeedBack(C_FeedBack_UserID,"
				+ " C_FeedBack_UserName, C_FeedBack_Content, C_FeedBack_Time,"
				+ " C_FeedBack_CheckResult,"
				+ " C_FeedBack_CheckAdminID, C_FeedBack_CheckTime) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		logger.info(sql);
		try {
			this.jdbcTemplate.update(sql,entity.getUserId(), entity.getUserName(),entity.getContent(),
					entity.getTime(),entity.getCheckResult(), entity.getCheckAdminId(),entity.getCheckTime());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void update(FeedBack entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "update C_FeedBack set C_FeedBack_UserID=?,"
				+ " C_FeedBack_UserName=?, C_FeedBack_Content=?,"
				+ " C_FeedBack_Time=?, C_FeedBack_CheckResult=?,"
				+ " C_FeedBack_CheckAdminID=?, C_FeedBack_CheckTime=? "
				+ " where C_FeedBack_ID = ?";
		logger.info(sql);
		try {
			this.jdbcTemplate.update(sql,entity.getUserId(), entity.getUserName(),
					entity.getContent(), entity.getTime(),entity.getCheckResult(), entity.getCheckAdminId(),
					entity.getCheckTime(),entity.getId());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	
	
	
	public Pager<FeedBack> getByPagerbefore(Pager<FeedBack> pager) {	
		if (pager == null) {
			pager = new Pager<FeedBack>();
		}
		Integer pageNumber = pager.getCurrentPage();
		Integer pageSize = pager.getPageSize();
		String propertyName = pager.getPropertyName();
		Object propertyValue = pager.getPropertyValue();
		String orderBy = pager.getOrderBy();
		Map<String, Object> queryMap = pager.getQueries();
		String orderType = pager.getOrderType() == OrderType.asc ? "asc": "desc";
		int firstResult = (pageNumber - 1) * pageSize;
		
		StringBuilder sqlBuilder = new StringBuilder("select * from C_FeedBack ");
		StringBuilder countTotal = new StringBuilder("select count(*) from C_FeedBack ");//用于查询总记录数
		//将属性组成的map转换为数据库中列名组成的map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//属性限制
		if (StringUtils.isNotEmpty(propertyName) && propertyValue!= null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(propertyName, propertyValue);
			sqlBuilder.append("where "+getSqlClause(params,"and",propertyMapper));
			countTotal.append("where "+getSqlClause(params,"and",propertyMapper));
			//
			paramMap.put(propertyMapper.get(propertyName), propertyValue);
			
			//处理查询条件
			if(queryMap != null && !queryMap.isEmpty()){
				sqlBuilder.append(" and "+getSqlClause(queryMap,"and",propertyMapper));
				countTotal.append(" and "+getSqlClause(queryMap,"and",propertyMapper));
				
				//
				for(String key : queryMap.keySet()){
					String filedName = propertyMapper.get(key);
					paramMap.put(filedName, queryMap.get(key));
				}
			}
		}
		else if (queryMap != null && !queryMap.isEmpty()) {
			sqlBuilder.append("where "+getSqlClause(queryMap,"and",propertyMapper));
			countTotal.append("where "+getSqlClause(queryMap,"and",propertyMapper));
			
			for(String key : queryMap.keySet()){
				String filedName = propertyMapper.get(key);
				paramMap.put(filedName, queryMap.get(key));
			}
		}
		
		//两个都为空
		sqlBuilder.append(" order by "+propertyMapper.get(orderBy)+" "+orderType 
				+ " limit "+firstResult+" ," +" "+pageSize);			
							
		logger.info(countTotal.toString());
		logger.info(sqlBuilder.toString());
		
		int totalCount = namedParameterJdbcTemplate.queryForInt(countTotal.toString(), paramMap);
		pager.setTotalCount(totalCount);		
		pager.setDataList(namedParameterJdbcTemplate.query(sqlBuilder.toString(), paramMap, new EntityMapper()));
		return pager;
	}
	
	
	@Override
	public Pager<FeedBack> getByPager(Pager<FeedBack> pager) {	
		if (pager == null) {
			pager = new Pager<FeedBack>();
		}
		Integer pageNumber = pager.getCurrentPage();
		Integer pageSize = pager.getPageSize();
		String propertyName = pager.getPropertyName();
		Object propertyValue = pager.getPropertyValue();
		String orderBy = pager.getOrderBy();
		Map<String, Object> queryMap = pager.getQueries();
		String orderType = pager.getOrderType() == OrderType.asc ? "asc": "desc";
		int firstResult = (pageNumber - 1) * pageSize;
		
		if(pager.getTotalCount() == 0) { //总记录数为0才查询总记录数
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_FeedBack ");
			StringBuilder countTotal = new StringBuilder("select count(*) from C_FeedBack ");//用于查询总记录数
			//将属性组成的map转换为数据库中列名组成的map
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//属性限制
			if (StringUtils.isNotEmpty(propertyName) && propertyValue!= null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(propertyName, propertyValue);
				sqlBuilder.append("where "+getSqlClause(params,"and",propertyMapper));
				countTotal.append("where "+getSqlClause(params,"and",propertyMapper));
				//
				paramMap.put(propertyMapper.get(propertyName), propertyValue);
				
				//处理查询条件
				if(queryMap != null && !queryMap.isEmpty()){
					sqlBuilder.append(" and "+getSqlClause(queryMap,"and",propertyMapper));
					countTotal.append(" and "+getSqlClause(queryMap,"and",propertyMapper));
					
					//
					for(String key : queryMap.keySet()){
						String filedName = propertyMapper.get(key);
						paramMap.put(filedName, queryMap.get(key));
					}
				}
			}
			else if (queryMap != null && !queryMap.isEmpty()) {
				sqlBuilder.append("where "+getSqlClause(queryMap,"and",propertyMapper));
				countTotal.append("where "+getSqlClause(queryMap,"and",propertyMapper));
				
				for(String key : queryMap.keySet()){
					String filedName = propertyMapper.get(key);
					paramMap.put(filedName, queryMap.get(key));
				}
			}
			
			//两个都为空
			sqlBuilder.append(" order by "+propertyMapper.get(orderBy)+" "+orderType 
					+ " limit "+firstResult+" ," +" "+pageSize);			
								
			logger.info(countTotal.toString());
			logger.info(sqlBuilder.toString());
			
			try {
				int totalCount = namedParameterJdbcTemplate.queryForInt(countTotal.toString(), paramMap);
				pager.setTotalCount(totalCount);		
				pager.setDataList(namedParameterJdbcTemplate.query(sqlBuilder.toString(), paramMap, new EntityMapper()));
				return pager;
				
			} catch (InvalidDataAccessApiUsageException e) {
				throw new DBRuntimeException("属性名错误",sqlBuilder.toString());
			}
			catch (DataAccessException e) {
				throw new DBRuntimeException(e,sqlBuilder.toString());
			}
			
		}else { //不用查询总记录数
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_FeedBack ");
			//将属性组成的map转换为数据库中列名组成的map
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//属性限制
			if (StringUtils.isNotEmpty(propertyName) && propertyValue!= null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(propertyName, propertyValue);
				sqlBuilder.append("where "+getSqlClause(params,"and",propertyMapper));
				//
				paramMap.put(propertyMapper.get(propertyName), propertyValue);
				
				//处理查询条件
				if(queryMap != null && !queryMap.isEmpty()){
					sqlBuilder.append(" and "+getSqlClause(queryMap,"and",propertyMapper));
					
					//
					for(String key : queryMap.keySet()){
						String filedName = propertyMapper.get(key);
						paramMap.put(filedName, queryMap.get(key));
					}
				}
			}
			else if (queryMap != null && !queryMap.isEmpty()) {
				sqlBuilder.append("where "+getSqlClause(queryMap,"and",propertyMapper));
				
				for(String key : queryMap.keySet()){
					String filedName = propertyMapper.get(key);
					paramMap.put(filedName, queryMap.get(key));
				}
			}
			
			//两个都为空
			sqlBuilder.append(" order by "+propertyMapper.get(orderBy)+" "+orderType 
					+ " limit "+firstResult+" ," +" "+pageSize);			
								
			logger.info(sqlBuilder.toString());
			
			try {
				pager.setDataList(namedParameterJdbcTemplate.query(sqlBuilder.toString(), paramMap, new EntityMapper()));
				return pager;
				
			} catch (InvalidDataAccessApiUsageException e) {
				throw new DBRuntimeException("属性名错误",sqlBuilder.toString());
			}
			catch (DataAccessException e) {
				throw new DBRuntimeException(e,sqlBuilder.toString());
			}
		}
		
	}

	@Override
	public void update(Map<String, Object> property, Map<String, Object> identity) {
		Assert.notEmpty(property, "map不能为空");
		Assert.notEmpty(identity, "map不能为空");
		String prefix = "update C_FeedBack set ";
		String sql = prefix + getSqlClause(property,",",propertyMapper) +" where " +getSqlClause(identity,"and",propertyMapper);
		logger.info(sql);
		
		//将属性组成的map转换为数据库中列名组成的map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(String key : property.keySet()){
			String filedName = propertyMapper.get(key);
			paramMap.put(filedName, property.get(key));
		}
		for(String key : identity.keySet()){
			String filedName = propertyMapper.get(key);
			paramMap.put(filedName, identity.get(key));
		}
		try {
			namedParameterJdbcTemplate.update(sql, paramMap);
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}


	@Override
	public void delete(Integer id) {
		Assert.notNull(id, "id 不能为空");
		String sql = "delete from C_FeedBack where C_FeedBack_ID=?";
		logger.info(sql);
		try {
			this.jdbcTemplate.update(sql,id);
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void delete(final Integer[] ids) {
		Assert.notEmpty(ids, "ids 不能为空");
		String sql = "delete from C_FeedBack where C_FeedBack_ID=?";
		logger.info(sql);
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					 ps.setInt(1, ids[i]);
				}
				@Override
				public int getBatchSize() {
					return ids.length;
				}
			});
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void save(final List<FeedBack> entityList) {
		Assert.notEmpty(entityList, "entityList不能为空");
		String sql = "insert into C_FeedBack(C_FeedBack_UserID,"
				+ " C_FeedBack_UserName, C_FeedBack_Content,"
				+ " C_FeedBack_Time, C_FeedBack_CheckResult,"
				+ " C_FeedBack_CheckAdminID, C_FeedBack_CheckTime) "
				+ "values (?, ?, ?, ?, ?, ?, ?)";	
		logger.info(sql);
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
	
				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setInt(1, entityList.get(i).getUserId());
					ps.setString(2, entityList.get(i).getUserName());
					ps.setString(3, entityList.get(i).getContent());
					ps.setLong(4, entityList.get(i).getTime());
					ps.setInt(5, entityList.get(i).getCheckResult());
					ps.setInt(6, entityList.get(i).getCheckAdminId());
					ps.setLong(7, entityList.get(i).getCheckTime());
				}
				@Override
				public int getBatchSize() {
					return entityList.size();
				}
				
			});
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	@Override
	public Integer getTotalCount(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(value, "属性值value不能为空");
		String sql = "select count(*) from C_FeedBack where "+propertyMapper.get(propertyName)+" = ?";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.queryForInt(sql, new Object[]{value},new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

}
