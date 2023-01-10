package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteUpdateOptionService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean updateOption(Vote request)
	{
		Vote voteOption = voteMapper.selectVoteOption(request.getVoteOptionId());
		
		if(voteOption == null)
		{
			throw new NullVoteException();
		}
		
		voteMapper.updateVoteOption(voteOption, request);
		
		return true;
	}
}
