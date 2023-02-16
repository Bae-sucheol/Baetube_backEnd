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
		// 동영상, 썸네일 UUID 생성.
		String videoUUID = UUIDUtil.createUUID();
		String thumbnailUUID = UUIDUtil.createUUID();
		
		// request에 생성한 UUID를 적용.
		request.setUrl(videoUUID);
		request.setThumbnail(thumbnailUUID);
		
		// 동영상 파일
		videoMaper.insert(request);
		
		return new String[] {videoUUID, thumbnailUUID};
	}
}
