package Baetube_backEnd.service.nestedreply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.NestedReplyMapper;

public class NestedReplyUpdateService
{
	@Autowired
	private NestedReplyMapper nestedReplyMapper;
	
	public boolean updateNestedReply(NestedReply request)
	{
		NestedReply nestedReply = nestedReplyMapper.selectByNestedReplyId(request.getNestedReplyId());
		
		if(nestedReply == null)
		{
			throw new NullReplyException();
		}
		
		nestedReplyMapper.updateComment(request.getNestedReplyId(), request.getComment());
		
		return true;
	}
}
