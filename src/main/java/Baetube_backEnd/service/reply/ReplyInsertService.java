package Baetube_backEnd.service.reply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyInsertService
{
	@Autowired
	private ReplyMapper replyMapper;
	
	public boolean insertReply(Reply request)
	{
		// ´ñ±Û °Ë»ç
		
		replyMapper.insert(request);
		
		return true;
	}
	
}
