package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoUpdateService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Transactional
	public boolean update(Video request)
	{
		Video video = videoMapper.selectByVideoId(request.getVideoId());
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		videoMapper.update(video, request);
		
		return true;
	}
}
