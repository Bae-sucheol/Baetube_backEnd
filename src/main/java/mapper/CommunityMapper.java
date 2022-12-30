package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Community;

public interface CommunityMapper
{
	public void insert(@Param("community") Community community);
	public void update(@Param("oldCommunity") Community oldCommunity, @Param("newCommunity") Community newCommunity);
	public void updateLike(Integer communityId, Integer value);
	public void updateHate(Integer communityId, Integer value);
	public void updateReplyCount(Integer communityId, Integer value);
	public List<Community> selectByChannel(Integer channelId);
}
