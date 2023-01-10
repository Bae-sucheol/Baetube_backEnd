package Baetube_backEnd.service.vote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteSelectOptionService
{
	@Autowired
	private VoteMapper voteMapper;
	
	public List<Vote> select(Integer request)
	{
		List<Vote> voteOptionList = voteMapper.selectVoteOptions(request);
		
		return voteOptionList;
	}
}
