package Baetube_backEnd.service.vote;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.DuplicateVoteOptionException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertOptionService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean insertOption(Vote request)
	{
		List<Vote> voteList = voteMapper.selectVoteOptions(request.getVoteId());
		
		if(voteList != null)
		{
			
			for(int i = 0; i < voteList.size(); i++)
			{
				if(request.getOption().equals(voteList.get(i).getOption()))
				{
					System.out.println("request : " + request.getOption() + " , voteList : " + voteList.get(i).getOption());
					throw new DuplicateVoteOptionException();
				}
			}
			
		}

		voteMapper.insertVoteOption(request);
		
		return true;
	}
}
