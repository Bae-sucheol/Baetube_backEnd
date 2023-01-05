package Baetube_backEnd.service.nestedReply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.mapper.NestedReplyMapper;

public class NestedReplyUpdateService
{
	@Autowired
	private NestedReplyMapper nestedReplyMapper;

	public void setNestedReplyMapper(NestedReplyMapper nestedReplyMapper)
	{
		this.nestedReplyMapper = nestedReplyMapper;
	}
	
	public boolean updateNestedReply(NestedReply request)
	{
		// ´ñ±Û °Ë»ç
		
		nestedReplyMapper.updateComment(request.getNestedReplyId(), request.getComment());
		
		return true;
	}
}
