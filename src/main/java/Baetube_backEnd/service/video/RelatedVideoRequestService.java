package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class RelatedVideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Transactional
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
