package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.NestedReply;

public interface NestedReplyMapper
{
	public void insert(@Param("nestedReply") NestedReply nestedReply);
	public void updateComment(@Param("nestedReplyId") Integer nestedReplyId, @Param("comment") String comment);
	public List<NestedReply> selectByReplyId(@Param("replyId") Integer replyId);
	public NestedReply selectByNestedReplyId(@Param("nestedReplyId") Integer nestedReplyId);
}
