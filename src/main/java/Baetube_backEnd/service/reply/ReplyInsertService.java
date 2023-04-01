package Baetube_backEnd.service.reply;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.ReplyMapper;
import Baetube_backEnd.service.fcm.FCMSendService;

public class ReplyInsertService
{
	@Autowired
	private ReplyMapper replyMapper;	
	@Autowired
	private ContentsMapper contentsMapper;
	
	public HashMap<String, String> insertReply(Reply request)
	{
		replyMapper.insert(request);
		
		String fcmToken = new String();
		Integer type = 0;
		Integer resultId = 0;
		
		contentsMapper.selectFCMTokenByContentsId(request.getAttachedId(), fcmToken, type, resultId);
		
		// 맵을 만들어서 반환한다.
		HashMap<String, String> result = new HashMap<>();
				
		result.put(FCMSendService.FCM_NOTIFICATION_REPLY, request.getReplyId().toString());
		result.put("fcmToken", fcmToken);
		result.put("type", type.toString());
		result.put("resultId", resultId.toString());
		
		return result;
	}
	
}
