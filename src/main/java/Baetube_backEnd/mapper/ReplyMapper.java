package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Reply;

/*
 * SELECT user_id, v_contents_id AS contents_id, NOW() AS `date` FROM CHANNEL NATURAL JOIN reply WHERE reply_id = 24;
 * 이걸로 프로시저를 최적화 할 수 있을 것 같다.
 */

public interface ReplyMapper
{
	public void insert(@Param("reply") Reply reply);
	public void updateComment(Integer replyId, String comment);
	public List<Reply> selectByContentsId(Long contentsId);
}
