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
		// 동영상, 썸네일 UUID 생성.
		String videoUUID = UUIDUtil.createUUID();
		String thumbnailUUID = UUIDUtil.createUUID();
		
		// request에 생성한 UUID를 적용.
		request.setUrl(videoUUID);
		request.setThumbnail(thumbnailUUID);
		
		// 동영상 파일
		videoMaper.insert(request);
		
		// 맵을 만들어서 반환한다.
		HashMap<String, String> result = new HashMap<>();
		
		result.put("insertType", "video");
		result.put("videoUUID", videoUUID);
		result.put("thumbnailUUID", thumbnailUUID);
		
		return result;
	}
}
