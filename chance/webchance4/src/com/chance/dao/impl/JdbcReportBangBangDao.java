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

import com.chance.dao.ReportBangBangDao;
import com.chance.domain.Pager;
import com.chance.domain.Pager.OrderType;
import com.chance.domain.ReportBangBang;


@Repository("reportBangBangDao")
public class JdbcReportBangBangDao extends JdbcBaseDao<ReportBangBang, Integer> implements ReportBangBangDao{
	
	/**
	 * 对象属性→数据库表字段的映射
	 */
	private static final Map<String, String> propertyMapper = new HashMap<String, String>();
	static{
		propertyMapper.put("id", "C_ReportBangBang_ID");
		propertyMapper.put("userId", "C_ReportBangBang_UserID");
		propertyMapper.put("userName", "C_ReportBangBang_UserName");
		propertyMapper.put("byUserId", "C_ReportBangBang_ByUserID");
		propertyMapper.put("byUserName", "C_ReportBangBang_ByUserName");
		propertyMapper.put("time", "C_ReportBangBang_Time");
		propertyMapper.put("bangBangId", "C_ReportBangBang_BangBangID");
		propertyMapper.put("reason", "C_ReportBangBang_Reason");
		propertyMapper.put("checkResult", "C_ReportBangBang_CheckResult");
		propertyMapper.put("checkAdminId", "C_ReportBangBang_CheckAdminID");
		propertyMapper.put("checkTime", "C_ReportBangBang_CheckTime");
	}

	private static final class EntityMapper implements RowMapper<ReportBangBang> {
	    public ReportBangBang mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ReportBangBang reportBangBang = new ReportBangBang();
	    	reportBangBang.setByUserId(rs.getInt("C_ReportBangBang_ByUserID"));
	    	reportBangBang.setByUserName(rs.getString("C_ReportBangBang_ByUserName"));
	    	reportBangBang.setCheckAdminId(rs.getInt("C_ReportBangBang_CheckAdminID"));
	    	reportBangBang.setCheckResult(rs.getInt("C_ReportBangBang_CheckResult"));
	    	reportBangBang.setCheckTime(rs.getLong("C_ReportBangBang_CheckTime"));
	    	reportBangBang.setBangBangId(rs.getInt("C_ReportBangBang_BangBangID"));
	    	reportBangBang.setReason(rs.getString("C_ReportBangBang_Reason"));
	    	reportBangBang.setId(rs.getInt("C_ReportBangBang_ID"));
	    	reportBangBang.setTime(rs.getLong("C_ReportBangBang_Time"));
	    	reportBangBang.setUserId(rs.getInt("C_ReportBangBang_UserID"));
	    	reportBangBang.setUserName(rs.getString("C_ReportBangBang_UserName"));
            return reportBangBang;
	    }
	}
	

	@Override
	public ReportBangBang get(Integer id) {
		Assert.notNull(id, "id 不能为空");
		String sql = "select * from C_ReportBangBang where C_ReportBangBang_ID = ?";
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
	public List<ReportBangBang> get(Integer[] ids) {
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
		
		String sql = "select * from C_ReportBangBang where C_ReportBangBang_ID in "+sb.toString() + " order by C_ReportBangBang_Time desc";
		logger.info(sql);
		try {
			return this.jdbcTemplate.query(sql,new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public ReportBangBang get(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名不能为空");
		Assert.notNull(value, "属性值不能为空");
		String sql = "select * from C_ReportBangBang where "+propertyMapper.get(propertyName)+" = ?";
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
	public List<ReportBangBang> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(value, "属性值value不能为空");
		String sql = "select * from C_ReportBangBang where "+propertyMapper.get(propertyName)+" = ? order by C_ReportBangBang_Time desc";
		logger.info(sql);
		try {
			return this.jdbcTemplate.query(sql, new Object[]{value},new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}
	
	@Override
	public ReportBangBang get(Map<String, Object> params) {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_ReportBangBang where ";
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
	public List<ReportBangBang> getList(Map<String, Object> params) {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_ReportBangBang where ";
		String sql = prefix + getSqlClause(params,"and",propertyMapper)+" order by C_ReportBangBang_Time desc";
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
	public List<ReportBangBang> getAll() {	
		String sql = "select * from C_ReportBangBang order by C_ReportBangBang_Time desc";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.query(sql, new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
	}

	@Override
	public void save(ReportBangBang entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "insert into C_ReportBangBang(C_ReportBangBang_UserID,"
				+ " C_ReportBangBang_UserName, C_ReportBangBang_ByUserID,"
				+ " C_ReportBangBang_ByUserName, C_ReportBangBang_Time,"
				+ " C_ReportBangBang_BangBangID, C_ReportBangBang_Reason, C_ReportBangBang_CheckResult,"
				+ " C_ReportBangBang_CheckAdminID, C_ReportBangBang_CheckTime) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		logger.info(sql);
		
		try {
			this.jdbcTemplate.update(sql,entity.getUserId(), entity.getUserName(),entity.getByUserId(),
					entity.getByUserName(), entity.getTime(),entity.getBangBangId(),entity.getReason(),
					entity.getCheckResult(), entity.getCheckAdminId(),entity.getCheckTime());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	@Override
	public void update(ReportBangBang entity) {
		Assert.notNull(entity, "entity不能为空");
		String sql = "update C_ReportBangBang set C_ReportBangBang_UserID=?,"
				+ " C_ReportBangBang_UserName=?, C_ReportBangBang_ByUserID=?,"
				+ " C_ReportBangBang_ByUserName=?, C_ReportBangBang_Time=?,"
				+ " C_ReportBangBang_BangBangID=?, C_ReportBangBang_Reason=?, C_ReportBangBang_CheckResult=?,"
				+ " C_ReportBangBang_CheckAdminID=?, C_ReportBangBang_CheckTime=? "
				+ " where C_ReportBangBang_ID = ?";
		logger.info(sql);
		
		try {
			this.jdbcTemplate.update(sql,entity.getUserId(), entity.getUserName(),
					entity.getByUserId(),entity.getByUserName(), entity.getTime(),
					entity.getBangBangId(),entity.getReason(),entity.getCheckResult(), entity.getCheckAdminId(),
					entity.getCheckTime(),entity.getId());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}
	
	@Override
	public Pager<ReportBangBang> getByPager(Pager<ReportBangBang> pager) {	
		if (pager == null) {
			pager = new Pager<ReportBangBang>();
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
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_ReportBangBang ");
			StringBuilder countTotal = new StringBuilder("select count(*) from C_ReportBangBang ");//用于查询总记录数
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
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_ReportBangBang ");
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
		String prefix = "update C_ReportBangBang set ";
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
		String sql = "delete from C_ReportBangBang where C_ReportBangBang_ID=?";
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
		String sql = "delete from C_ReportBangBang where C_ReportBangBang_ID=?";
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
	public void save(final List<ReportBangBang> entityList) {
		Assert.notEmpty(entityList, "entityList不能为空");
		String sql = "insert into C_ReportBangBang(C_ReportBangBang_UserID,"
				+ " C_ReportBangBang_UserName, C_ReportBangBang_ByUserID,"
				+ " C_ReportBangBang_ByUserName, C_ReportBangBang_Time,"
				+ " C_ReportBangBang_BangBangID, C_ReportBangBang_Reason, C_ReportBangBang_CheckResult,"
				+ " C_ReportBangBang_CheckAdminID, C_ReportBangBang_CheckTime) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	
		logger.info(sql);
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){
	
				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					ps.setInt(1, entityList.get(i).getUserId());
					ps.setString(2, entityList.get(i).getUserName());
					ps.setInt(3, entityList.get(i).getByUserId());
					ps.setString(4, entityList.get(i).getByUserName());
					ps.setLong(5, entityList.get(i).getTime());
					ps.setInt(6, entityList.get(i).getBangBangId());
					ps.setString(7, entityList.get(i).getReason());
					ps.setInt(8, entityList.get(i).getCheckResult());
					ps.setInt(9, entityList.get(i).getCheckAdminId());
					ps.setLong(10, entityList.get(i).getCheckTime());
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
		String sql = "select count(*) from C_ReportBangBang where "+propertyMapper.get(propertyName)+" = ?";
		logger.info(sql);
		
		try {
			return this.jdbcTemplate.queryForInt(sql, new Object[]{value},new EntityMapper());
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

}
