package Baetube_backEnd.service.nestedreply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.NestedReplyMapper;

public class NestedReplySelectService
{
	@Autowired
	private NestedReplyMapper nestedReplyMapper;
	
	public List<NestedReply> selectNestedReply(Integer request)
	{
		List<NestedReply> nestedReplyList = nestedReplyMapper.selectByReplyId(request);
		
		if(nestedReplyList == null)
		{
			throw new NullReplyException();
		}   
		
		return nestedReplyList;
	}
}
