package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;

public class CastVoteOptionService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean castVoteOption(Vote request)
	{
		voteMapper.castVoteOption(request.getCommunityId(), request.getVoteId(), request.getVoteOptionId());
		
		return true;
	}
}
