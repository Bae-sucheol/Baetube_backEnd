package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteDeleteOptionService
{
	@Autowired
	private VoteMapper voteMapper;

	public void setVoteMapper(VoteMapper voteMapper)
	{
		this.voteMapper = voteMapper;
	}
	
	@Transactional
	public boolean deleteOption(Vote request)
	{
		Vote vote = voteMapper.selectVoteOption(request.getVoteOptionId());
		
		if(vote == null)
		{
			throw new NullVoteException();
		}
		
		voteMapper.deleteVoteOption(request);
		
		return true;
	}
}
