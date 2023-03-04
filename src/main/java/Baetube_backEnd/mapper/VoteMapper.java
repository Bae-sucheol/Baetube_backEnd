package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Vote;

public interface VoteMapper
{
	public Integer insertVote(@Param("vote") Vote vote);
	public Integer insertVoteOption(@Param("voteOption") Vote voteOption);
	public void insertVoteOptionMulti(@Param("voteOptionList") List<Vote> voteOptionList);
	public void updateVote(@Param("oldVote") Vote oldVote, @Param("newVote") Vote newVote);
	public void updateVoteOption(@Param("oldVoteOption") Vote oldVoteOption, @Param("newVoteOption") Vote newVoteOption);
	public void updateVoteOptionCount(@Param("vote") Vote vote);
	public void deleteVote(@Param("voteId") Integer voteId);
	public void deleteVoteOption(@Param("vote") Vote vote);
	public void deleteVoteOptionMulti(@Param("voteOptionList") List<Vote> voteOptionList);
	public List<Vote> selectVoteOptions(@Param("voteId") Integer voteId);
	public Vote selectVote(@Param("voteId") Integer voteId);
	public Vote selectVoteByCommunityId(@Param("communityId") Integer communityId);
	public Vote selectVoteOption(@Param("voteOptionId") Integer voteOptionId);
	public void castVoteOption(@Param("channelId") Integer channelId, @Param("voteId") Integer voteId, @Param("voteOptionId") Integer voteOptionId);
	public void cancelVoteOption(@Param("channelId") Integer channelId, @Param("voteId") Integer voteId, @Param("voteOptionId") Integer voteOptionId);
}
