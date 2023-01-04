package Baetube_backEnd.service.community;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityInsertService
{
	@Autowired
	private CommunityMapper communityMapper;

	public void setCommunityMapper(CommunityMapper communityMapper)
	{
		this.communityMapper = communityMapper;
	}
	
	public boolean insertCommunity(Community request)
	{
		communityMapper.insert(request);
		
		return true;
	}
}
