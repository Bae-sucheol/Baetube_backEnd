package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;

public class CancelVoteOptionService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean cancelVoteOption(Vote request)
	{
		voteMapper.cancelVoteOption(request.getCommunityId(), request.getVoteId(), request.getVoteOptionId());
		
		return true;
	}
}
