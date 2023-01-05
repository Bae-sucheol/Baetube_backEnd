package Baetube_backEnd.service.reply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyUpdateService
{
	@Autowired
	private ReplyMapper replyMapper;

	public void setReplyMapper(ReplyMapper replyMapper)
	{
		this.replyMapper = replyMapper;
	}
	
	public boolean updateReply(Reply request)
	{
		// ´ñ±Û °Ë»ç
		
		replyMapper.updateComment(request.getReplyId(), request.getComment());
		
		return true;
	}
	
}
