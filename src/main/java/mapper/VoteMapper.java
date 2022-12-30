package mapper;

import org.apache.ibatis.annotations.Param;

import dto.Vote;

public interface VoteMapper
{
	public Integer insertVote(@Param("vote") Vote vote);
	public Integer insertVoteOption(@Param("voteOption") Vote voteOption);
	public void updateVote(@Param("oldVote") Vote vote, @Param("newVote") Vote newVote);
	public void updateVoteOption(@Param("oldVoteOption") Vote oldVoteOption, @Param("newVoteOption") Vote newVoteOption);
	public void updateVoteOptionCount(Integer value, Integer voteId, Integer voteOptionId);
	public void deleteVote(Integer voteId);
	public void deleteVoteOption(Integer voteId, Integer VoteOptionId);
}
