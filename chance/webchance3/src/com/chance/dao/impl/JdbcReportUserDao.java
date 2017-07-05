package com.chance.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.chance.dao.ReportUserDao;
import com.chance.domain.Pager;
import com.chance.domain.ReportChatMsg;
import com.chance.domain.Pager.OrderType;
import com.chance.domain.ReportUser;


@Repository("reportUserDao")
public class JdbcReportUserDao implements ReportUserDao{
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcReportUserDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/**
	 * 对象属性→数据库表字段的映射
	 */
	private static final Map<String, String> propertyMapper = new HashMap<String, String>();
	static{
		propertyMapper.put("id", "C_ReportUser_ID");
		propertyMapper.put("userId", "C_ReportUser_UserID");
		propertyMapper.put("userName", "C_ReportUser_UserName");
		propertyMapper.put("byUserId", "C_ReportUser_ByUserID");
		propertyMapper.put("byUserName", "C_ReportUser_ByUserName");
		propertyMapper.put("time", "C_ReportUser_Time");
		propertyMapper.put("type", "C_ReportUser_Type");
		propertyMapper.put("reason", "C_ReportUser_Reason");

		propertyMapper.put("checkResult", "C_ReportUser_CheckResult");
		propertyMapper.put("checkAdminId", "C_ReportUser_CheckAdminID");
		propertyMapper.put("checkTime", "C_ReportUser_CheckTime");
	}

	private static final class EntityMapper implements RowMapper<ReportUser> {
	    public ReportUser mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ReportUser reportUser = new ReportUser();
	    	reportUser.setByUserId(rs.getInt("C_ReportUser_ByUserID"));
	    	reportUser.setByUserName(rs.getString("C_ReportUser_ByUserName"));
	    	reportUser.setCheckAdminId(rs.getInt("C_ReportUser_CheckAdminID"));
	    	reportUser.setCheckResult(rs.getInt("C_ReportUser_CheckResult"));
	    	reportUser.setCheckTime(rs.getLong("C_ReportUser_CheckTime"));
	    	reportUser.setId(rs.getInt("C_ReportUser_ID"));
	    	reportUser.setTime(rs.getLong("C_ReportUser_Time"));
	    	reportUser.setUserId(rs.getInt("C_ReportUser_UserID"));
	    	reportUser.setUserName(rs.getString("C_ReportUser_UserName"));
	    	reportUser.setReason(rs.getString("C_ReportUser_Reason"));
	    	reportUser.setType(rs.getInt("C_ReportUser_Type"));
            return reportUser;
	    }
	}
	
	private static final class ChatMsgMapper implements RowMapper<ReportChatMsg> {
	    public ReportChatMsg mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ReportChatMsg result = new ReportChatMsg();
	    	result.setId(rs.getInt("C_ReportChatMsg_ID"));
	    	result.setMsgContent(rs.getString("C_ReportChatMsg_MsgContent"));
	    	result.setReceiveCID(rs.getInt("C_ReportChatMsg_ReceiveCID"));
	    	result.setReportUserId(rs.getInt("C_ReportChatMsg_ReportUserID"));
	    	result.setSendCID(rs.getInt("C_ReportChatMsg_SendCID"));
	    	result.setSendTime(rs.getLong("C_ReportChatMsg_SendTime"));
	    	result.setType(rs.getInt("C_ReportChatMsg_Type"));
            return result;
	    }
	}
	

	@Override
	public ReportUser get(Integer id, boolean isNeedChatMsg) {
		Assert.notNull(id, "id 不能为空");
		String sql = "select * from C_ReportUser where C_ReportUser_ID = ?";
		logger.info(sql);
		ReportUser result =  this.jdbcTemplate.queryForObject(sql,new Object[]{id},new EntityMapper());
		if(isNeedChatMsg){
			result.setMsgs(this.getChatMsg(id));
		}
		return result;
	}
	
	/**
	 * 会保存关联的聊天消息
	 */
	@Override
	@Transactional
	public void save(final ReportUser entity) {
		Assert.notNull(entity, "entity不能为空");
		final String sql = "insert into C_ReportUser(C_ReportUser_UserID,"
				+ " C_ReportUser_UserName, C_ReportUser_ByUserID,"
				+ " C_ReportUser_ByUserName, C_ReportUser_Time,"
				+ " C_ReportUser_Type, C_ReportUser_Reason, C_ReportUser_CheckResult,"
				+ " C_ReportUser_CheckAdminID, C_ReportUser_CheckTime) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		logger.info(sql);

			
		//插入并获取主键
		  KeyHolder keyHolder = new GeneratedKeyHolder();  
		  jdbcTemplate.update(new PreparedStatementCreator() {  
				@Override
				public PreparedStatement createPreparedStatement(Connection connection)
						throws SQLException {
		               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
		               ps.setInt(1, entity.getUserId());
		               ps.setString(2, entity.getUserName());
		               ps.setInt(3, entity.getByUserId());
		               ps.setString(4, entity.getByUserName());
		               ps.setLong(5, entity.getTime());
		               ps.setInt(6, entity.getType());
		               ps.setString(7, entity.getReason());
		               ps.setInt(8, entity.getCheckResult());
		               ps.setInt(9, entity.getCheckAdminId());
		               ps.setLong(10, entity.getCheckTime());
		               return ps; 
				}  
		    }, keyHolder);  
		   //获取插入后的主键   
		   final Integer generatedId = keyHolder.getKey().intValue();

		   //消息不为空,插入到另外一张表中
		   if(entity.getMsgs() != null && !entity.getMsgs().isEmpty()){
			   
			   String sql2 = "insert into C_ReportChatMsg(C_ReportChatMsg_ReportUserID,C_ReportChatMsg_SendCID," +
			   		"C_ReportChatMsg_ReceiveCID,C_ReportChatMsg_MsgContent,C_ReportChatMsg_Type," +
			   		"C_ReportChatMsg_SendTime) values (?, ?, ?, ?, ?, ?)";
			   logger.info(sql2);
			   
			   final List<ReportChatMsg> entityList = entity.getMsgs();
			   
			   jdbcTemplate.batchUpdate(sql2, new BatchPreparedStatementSetter(){
					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, generatedId);
						ps.setInt(2, entityList.get(i).getSendCID());
						ps.setInt(3, entityList.get(i).getReceiveCID());
						ps.setString(4, entityList.get(i).getMsgContent());
						ps.setInt(5, entityList.get(i).getType());
						ps.setLong(6, entityList.get(i).getSendTime());
					}
					@Override
					public int getBatchSize() {
						return entityList.size();
					}
				});
		   }
	}

	
	@Override
	public Pager<ReportUser> getByPager(Pager<ReportUser> pager) {	
		if (pager == null) {
			pager = new Pager<ReportUser>();
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
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_ReportUser ");
			StringBuilder countTotal = new StringBuilder("select count(*) from C_ReportUser ");//用于查询总记录数
			//将属性组成的map转换为数据库中列名组成的map
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//属性限制
			if (StringUtils.isNotEmpty(propertyName) && propertyValue!= null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(propertyName, propertyValue);
				sqlBuilder.append("where "+DaoUtils.getSqlClause(params,"and",propertyMapper));
				countTotal.append("where "+DaoUtils.getSqlClause(params,"and",propertyMapper));
				//
				paramMap.put(propertyMapper.get(propertyName), propertyValue);
				
				//处理查询条件
				if(queryMap != null && !queryMap.isEmpty()){
					sqlBuilder.append(" and "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
					countTotal.append(" and "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
					
					//
					for(String key : queryMap.keySet()){
						String filedName = propertyMapper.get(key);
						paramMap.put(filedName, queryMap.get(key));
					}
				}
			}
			else if (queryMap != null && !queryMap.isEmpty()) {
				sqlBuilder.append("where "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
				countTotal.append("where "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
				
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
			
			StringBuilder sqlBuilder = new StringBuilder("select * from C_ReportUser ");
			//将属性组成的map转换为数据库中列名组成的map
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//属性限制
			if (StringUtils.isNotEmpty(propertyName) && propertyValue!= null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(propertyName, propertyValue);
				sqlBuilder.append("where "+DaoUtils.getSqlClause(params,"and",propertyMapper));
				//
				paramMap.put(propertyMapper.get(propertyName), propertyValue);
				
				//处理查询条件
				if(queryMap != null && !queryMap.isEmpty()){
					sqlBuilder.append(" and "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
					
					//
					for(String key : queryMap.keySet()){
						String filedName = propertyMapper.get(key);
						paramMap.put(filedName, queryMap.get(key));
					}
				}
			}
			else if (queryMap != null && !queryMap.isEmpty()) {
				sqlBuilder.append("where "+DaoUtils.getSqlClause(queryMap,"and",propertyMapper));
				
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
	public List<ReportChatMsg> getChatMsg(Integer id) {
		Assert.notNull(id, "id 不能为空");
		String sql = "select * from C_ReportChatMsg where C_ReportChatMsg_ReportUserID = ? order by C_ReportChatMsg_SendTime asc";
		logger.info(sql);
		return this.jdbcTemplate.query(sql, new Object[]{id},new ChatMsgMapper());
	}

	@Override
	public void save(List<ReportUser> entityList) {
		Assert.notEmpty(entityList, "entityList不能为空");
		for (int i = 0; i < entityList.size(); i++) {
			this.save(entityList.get(i));
		}
		
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "属性名propertyName不能为空");
		Assert.notNull(value, "属性值value不能为空");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.getList(map).size() > 0 ? true: false; //本地数据有重复的,所有如此实现
	}

	@Override
	public List<ReportUser> getList(Map<String, Object> params) {
		Assert.notEmpty(params, "map不能为空");
		String prefix = "select * from C_ReportUser where ";
		String sql = prefix + DaoUtils.getSqlClause(params,"and",propertyMapper) + " order by C_ReportUser_Time desc";
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
	public void delete(Integer id) {
		Assert.notNull(id, "id 不能为空");
		String sql = "delete from C_ReportUser where C_ReportUser_ID=?";
		logger.info(sql);
		
		try {
			this.jdbcTemplate.update(sql,id);
		} catch (DataAccessException e) {
			throw new DBRuntimeException(e,sql);
		}
		
	}

	@Override
	public void update(Map<String, Object> property,
			Map<String, Object> identity) {
		Assert.notEmpty(property, "map不能为空");
		Assert.notEmpty(identity, "map不能为空");
		String prefix = "update C_ReportUser set ";
		String sql = prefix + DaoUtils.getSqlClause(property,",",propertyMapper) +" where " 
					+DaoUtils.getSqlClause(identity,"and",propertyMapper);
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

}
