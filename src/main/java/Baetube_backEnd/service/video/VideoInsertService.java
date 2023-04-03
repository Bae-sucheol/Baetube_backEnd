package Baetube_backEnd.service.video;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.fcm.FCMSendService;

public class VideoInsertService
{
	@Autowired
	private VideoMapper videoMapper;
	
	public HashMap<String, String> insert(Video request)
	{
		// ������, ����� UUID ����.
		String videoUUID = UUIDUtil.createUUID();
		String thumbnailUUID = UUIDUtil.createUUID();
		
		// request�� ������ UUID�� ����.
		request.setUrl(videoUUID);
		request.setThumbnail(thumbnailUUID);
		
		// ������ ����
		videoMapper.insert(request);
		
		// ���� ���� ��ȯ�Ѵ�.
		HashMap<String, String> result = new HashMap<>();
		
		result.put(FCMSendService.FCM_NOTIFICATION_VIDEO, request.getVideoId().toString());
		result.put("contentsId", request.getContentsId().toString());
		result.put("insertType", "video");
		result.put("videoUUID", videoUUID);
		result.put("thumbnailUUID", thumbnailUUID);
		
		return result;
	}
}
