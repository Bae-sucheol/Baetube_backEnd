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
		// ������ URL �����ϴ� �κ�
		
		// ������ ����
		
		
		videoMaper.insert(request);
		
		return true;
	}
}
