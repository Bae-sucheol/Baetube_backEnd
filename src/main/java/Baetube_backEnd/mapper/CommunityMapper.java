package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import Baetube_backEnd.dto.Community;

public interface CommunityMapper
{
	public void insert(@Param("community") Community community);
	public void update(@Param("oldCommunity") Community oldCommunity, @Param("newCommunity") Community newCommunity);
	public void delete(@Param("communityId") Integer communityId);
	public List<Community> selectByChannel(@Param("channelId") Integer channelId, @Param("requestChannelId") Integer requestChannelId);
	public Community selectByCommunityId(@Param("communityId") Integer communityId);
}
