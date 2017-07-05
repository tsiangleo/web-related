package com.chance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chance.domain.TeamInfoPending;
import com.chance.monitor.model.TeamInfoPendingModel;
import com.chance.remote.RemoteDataAccessException;
import com.chance.remote.RemoteTeamInfoPendingHandler;
import com.chance.service.TeamInfoPendingService;


@Service("teamInfoPendingService")
public class TeamInfoPendingServiceImpl  implements TeamInfoPendingService{

	private static final Logger log = LoggerFactory.getLogger(TeamInfoPendingServiceImpl.class);
	
	@Autowired
	private RemoteTeamInfoPendingHandler remoteTeamInfoPendingHandler;

	@Override
	public List<TeamInfoPending> getList(int number)throws BusinessException{
		try {
			return remote2Domain(remoteTeamInfoPendingHandler.getTeamInfoPending(number));
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public void audit(int teamid, int isPass, String reason) throws BusinessException{
		try {
			remoteTeamInfoPendingHandler.audiaTeamInfoPending(teamid, isPass, reason);
		} catch (RemoteDataAccessException e) {
			throw new BusinessException(e.getMessage(),e);
		}
		
	}
	private TeamInfoPending remote2Domain(TeamInfoPendingModel remoteModel){
		if (remoteModel == null) {
			return null;
		}
		TeamInfoPending result = new TeamInfoPending();
		result.setDesc(remoteModel.getDesc());
		result.setIcon(remoteModel.getIcon());
		result.setLevel(remoteModel.getLevel());
		result.setOwnCid(remoteModel.getOwnCid());
		result.setOwnName(remoteModel.getOwnName());
		result.setTags(remoteModel.getTags());
		result.setTeamId(remoteModel.getTeamId());
		result.setTeamName(remoteModel.getTeamName());
		result.setTime(remoteModel.getTime());
		return result;
	}
	
	private List<TeamInfoPending> remote2Domain(List<TeamInfoPendingModel> remoteModel){
		if (remoteModel == null) {
			return null;
		}
		List<TeamInfoPending> result = new ArrayList<TeamInfoPending>();
		for (TeamInfoPendingModel remote : remoteModel) {
			result.add(remote2Domain(remote));
		}
		return result;
	}
	
	

}
