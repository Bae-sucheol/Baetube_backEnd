package Baetube_backEnd.service.nestedreply;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.mapper.NestedReplyMapper;

public class NestedReplyInsertService
{
	@Autowired
	private NestedReplyMapper nestedReplyMapper;
	
	public boolean insertNestedReply(NestedReply request)
	{
		nestedReplyMapper.insert(request);
		
		return true;
	}
}
