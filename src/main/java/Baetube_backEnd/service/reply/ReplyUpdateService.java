package Baetube_backEnd.service.reply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyUpdateService
{
	@Autowired
	private ReplyMapper replyMapper;
	
	public boolean updateReply(Reply request)
	{
		Reply reply = replyMapper.selectByReplyId(request.getReplyId());
		
		if(reply == null)
		{
			throw new NullReplyException();
		}
		
		replyMapper.updateComment(request.getReplyId(), request.getComment());
		
		return true;
	}
	
}
