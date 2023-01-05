package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Vote;

public interface VoteMapper
{
	public Integer insertVote(@Param("vote") Vote vote);
	public Integer insertVoteOption(@Param("voteOption") Vote voteOption);
	public void insertVoteOptionMulti(List<Vote> voteOptionList);
	public void updateVote(@Param("oldVote") Vote oldVote, @Param("newVote") Vote newVote);
	public void updateVoteOption(@Param("oldVoteOption") Vote oldVoteOption, @Param("newVoteOption") Vote newVoteOption);
	public void updateVoteOptionCount(@Param("vote") Vote vote);
	public void deleteVote(Integer voteId);
	public void deleteVoteOption(@Param("vote") Vote vote);
	public void deleteVoteOptionMulti(List<Vote> voteOptionList);
	public List<Vote> selectVoteOptions(Integer voteId);
	public Vote selectVote(Integer voteId);
	public Vote selectVoteOption(Integer voteOptionId);
}
