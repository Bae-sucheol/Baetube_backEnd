package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class HistoryVideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Transactional
	public List<Video> requestVideo(Integer userId)
	{
		List<Video> videoList = videoMapper.selectHistoryVideo(userId);
		
		if(videoList == null)
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}
	
	@Transactional
	public List<Video> requestVideoKeywords(Integer userId, String keywords)
	{
		List<Video> videoList = videoMapper.selectHistoryVideoKeywords(userId, keywords);
		
		if(videoList == null)
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}


}
