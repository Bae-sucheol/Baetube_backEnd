package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.NestedReply;

public interface NestedReplyMapper
{
	public void insert(@Param("nestedreply") NestedReply nestedreply);
	public void updateComment(Integer nestedReplyId, String comment);
	public void updateLike(Integer nestedReplyId, Integer value);
	public void updateHate(Integer nestedReplyId, Integer value);
	public List<NestedReply> selectByContentsId(Integer replyId);
}
