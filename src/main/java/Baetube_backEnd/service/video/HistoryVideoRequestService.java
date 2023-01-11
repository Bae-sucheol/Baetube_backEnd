package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class HistoryVideoRequestService implements VideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Override
	public List<Video> requestVideo(Integer userId)
	{
		List<Video> videoList = videoMapper.selectHistoryVideo(userId);
		
		if(videoList == null)
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}

}
