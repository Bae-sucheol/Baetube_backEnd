package Baetube_backEnd.service.nestedreply;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.mapper.ReplyMapper;
import Baetube_backEnd.service.fcm.FCMSendService;

public class NestedReplyInsertService
{
	@Autowired
	private NestedReplyMapper nestedReplyMapper;
	@Autowired
	private ReplyMapper replyMapper;	
	@Autowired
	private ContentsMapper contentsMapper;
	
	public HashMap<String, String> insertNestedReply(NestedReply request)
	{
		nestedReplyMapper.insert(request);
		
		Reply reply = replyMapper.selectByReplyId(request.getNestedReplyId());
		
		String fcmToken = new String();
		Integer type = 0;
		Integer resultId = 0;
		
		contentsMapper.selectFCMTokenByContentsId(reply.getContentsId(), fcmToken, type, resultId);
		
		// 맵을 만들어서 반환한다.
		HashMap<String, String> result = new HashMap<>();
						
		result.put(FCMSendService.FCM_NOTIFICATION_REPLY, request.getReplyId().toString());
		result.put("fcmToken", fcmToken);
		result.put("type", type.toString());
		result.put("resultId", resultId.toString());
		
		return result;
	}
}
