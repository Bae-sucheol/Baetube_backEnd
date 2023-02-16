package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoInsertService
{
	@Autowired
	private VideoMapper videoMaper;
	
	public String[] insert(Video request)
	{
		// ������, ����� UUID ����.
		String videoUUID = UUIDUtil.createUUID();
		String thumbnailUUID = UUIDUtil.createUUID();
		
		// request�� ������ UUID�� ����.
		request.setUrl(videoUUID);
		request.setThumbnail(thumbnailUUID);
		
		// ������ ����
		videoMaper.insert(request);
		
		return new String[] {videoUUID, thumbnailUUID};
	}
}
