package Baetube_backEnd.service.vote;

import java.util.HashMap;

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
	public HashMap<String , String> insert(Vote request) throws DuplicateVoteException
	{
		Vote vote = voteMapper.selectVoteByCommunityId(request.getCommunityId());
		
		if(vote != null)
		{
			throw new DuplicateVoteException();
		}
		
		voteMapper.insertVote(request);
		
		HashMap<String , String> result = new HashMap<>();
		
		result.put("insertType", "vote");
		result.put("voteId", String.valueOf(request.getVoteId()));
		
		return result;
	}
}
