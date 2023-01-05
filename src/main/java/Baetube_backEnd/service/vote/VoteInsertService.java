package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertService
{
	@Autowired
	private VoteMapper voteMapper;

	public void setVoteMapper(VoteMapper voteMapper)
	{
		this.voteMapper = voteMapper;
	}
	
	@Transactional
	public boolean insert(Vote request)
	{
		voteMapper.insertVote(request);
		
		return true;
	}
}
