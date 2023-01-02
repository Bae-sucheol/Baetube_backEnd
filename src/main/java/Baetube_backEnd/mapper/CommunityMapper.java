package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Community;

public interface CommunityMapper
{
	public void insert(@Param("community") Community community);
	public void update(@Param("oldCommunity") Community oldCommunity, @Param("newCommunity") Community newCommunity);
	public List<Community> selectByChannel(Integer channelId);
}