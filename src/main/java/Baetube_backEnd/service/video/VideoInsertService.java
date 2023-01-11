package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoInsertService
{
	@Autowired
	private VideoMapper videoMaper;
	
	public boolean insert(Video request)
	{
		// 동영상 URL 생성하는 부분
		
		// 동영상 파일
		
		
		videoMaper.insert(request);
		
		return true;
	}
}
