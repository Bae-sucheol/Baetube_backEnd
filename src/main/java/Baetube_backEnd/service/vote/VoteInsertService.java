package Baetube_backEnd.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.DuplicateVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertService
{
	@Autowired
	private VoteMapper voteMapper;
	
	@Transactional
	public boolean insert(Vote request) throws DuplicateVoteException
	{
		Vote vote = voteMapper.selectVoteByCommunityId(request.getCommunityId());
		
		if(vote != null)
		{
			throw new DuplicateVoteException();
		}
		
		voteMapper.insertVote(request);
		
		return true;
	}
}
