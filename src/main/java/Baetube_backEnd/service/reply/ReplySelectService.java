package Baetube_backEnd.service.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplySelectService
{
	@Autowired
	private ReplyMapper replyMapper;

	public void setReplyMapper(ReplyMapper replyMapper)
	{
		this.replyMapper = replyMapper;
	}
	
	public List<Reply> selectReply(Long request)
	{
		List<Reply> replyList = replyMapper.selectByContentsId(request);
		
		if(replyList == null)
		{
			throw new NullReplyException();
		}   
		
		return replyList;
	}
	
}
