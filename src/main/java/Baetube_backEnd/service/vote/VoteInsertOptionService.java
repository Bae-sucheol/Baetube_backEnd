package Baetube_backEnd.service.vote;

import static org.junit.Assert.format;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.DuplicateVoteOptionException;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertOptionService
{
	@Autowired
	private VoteMapper voteMapper;

	public void setVoteMapper(VoteMapper voteMapper)
	{
		this.voteMapper = voteMapper;
	}
	
	@Transactional
	public boolean insertOption(Vote request)
	{
		List<Vote> voteList = voteMapper.selectVoteOptions(request.getVoteId());
		
		for(int i = 0; i < voteList.size(); i++)
		{
			if(request.getOption().equals(voteList.get(i).getOption()))
			{
				throw new DuplicateVoteOptionException();
			}
		}
		
		voteMapper.insertVoteOption(request);
		
		return true;
	}
}
