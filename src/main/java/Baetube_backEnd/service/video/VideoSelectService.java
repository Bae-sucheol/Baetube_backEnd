package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Transactional
	public List<Video> selectByKeywords(String request) throws NullVideoException
	{
		List<Video> videoList = videoMapper.selectByKeywords(request);
		
		if(videoList == null || videoList.isEmpty())
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}
}
