package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoSelectService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Transactional
	public Video selectByVideoId(Integer request) throws NullVideoException
	{
		Video video = videoMapper.selectByVideoId(request);
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		return video;
	}
}
