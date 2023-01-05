package Baetube_backEnd.service.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityDeleteService
{
	@Autowired
	private CommunityMapper communityMapper;

	public void setCommunityMapper(CommunityMapper communityMapper)
	{
		this.communityMapper = communityMapper;
	}
	
	@Transactional
	public boolean deleteCommunity(Integer request)
	{
		Community community = communityMapper.selectByCommunityId(request);
		
		if(community == null)
		{
			throw new NullCommunityException();
		}
		
		communityMapper.delete(request);
		
		return true;
	}
}
