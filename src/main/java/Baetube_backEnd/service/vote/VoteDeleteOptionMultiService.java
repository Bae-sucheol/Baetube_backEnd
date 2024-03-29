package Baetube_backEnd.service.vote;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteDeleteOptionMultiService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean deleteOptionMulti(List<Vote> request)
	{
		List<Vote> voteOptionList = voteMapper.selectVoteOptions(request.get(0).getVoteId());
		
		if(voteOptionList != null && !checkMatched(request, voteOptionList))
		{
			throw new NullVoteException();
		}
		
		voteMapper.deleteVoteOptionMulti(request);
		
		return true;
	}
	
	private boolean checkMatched(List<Vote> smaller, List<Vote> bigger)
	{
		for (int i = 0; i < smaller.size(); i++)
		{
			boolean isMatched = false;
			
			for (int j = 0; j < bigger.size(); j++)
			{
				if(smaller.get(i).getVoteOptionId() == bigger.get(j).getVoteOptionId())
				{
					isMatched = true;
					break;
				}
			}
			
			if(!isMatched)
			{
				return false;
			}
		}
		
		return true;
	}
	
}
