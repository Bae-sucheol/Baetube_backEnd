package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Reply;

public interface ReplyMapper
{
	public void insert(@Param("reply") Reply reply, Integer type);
	public void updateComment(Integer replyId, String comment);
	public void updateLike(Integer replyId, Integer value);
	public void updateHate(Integer replyId, Integer value);
	public void updateNestedCount(Integer replyId, Integer value);
	public List<Reply> selectByContentsId(Integer contentsId);
}
