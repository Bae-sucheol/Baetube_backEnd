package Baetube_backEnd.service.video;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoInsertService
{
	@Autowired
	private VideoMapper videoMaper;
	
	public HashMap<String, String> insert(Video request)
	{
		// ������, ����� UUID ����.
		String videoUUID = UUIDUtil.createUUID();
		String thumbnailUUID = UUIDUtil.createUUID();
		
		// request�� ������ UUID�� ����.
		request.setUrl(videoUUID);
		request.setThumbnail(thumbnailUUID);
		
		// ������ ����
		videoMaper.insert(request);
		
		// ���� ���� ��ȯ�Ѵ�.
		HashMap<String, String> result = new HashMap<>();
		
		result.put("insertType", "video");
		result.put("videoUUID", videoUUID);
		result.put("thumbnailUUID", thumbnailUUID);
		
		return result;
	}
}
