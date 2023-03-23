package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class RelatedVideoRequestService implements VideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public List<Video> requestVideo(Integer videoId)
	{
		List<Video> videoList = videoMapper.selectRelatedVideo(videoId);
		
		if(videoList == null)
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}
}
