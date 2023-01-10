package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteUpdateService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean update(Vote request)
	{
		Vote vote = voteMapper.selectVote(request.getVoteId());
		
		if(vote == null)
		{
			throw new NullVoteException();
		}
		
		voteMapper.updateVote(vote, request);
		
		return true;
	}
}
