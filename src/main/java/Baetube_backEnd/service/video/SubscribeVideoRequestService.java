package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class SubscribeVideoRequestService implements VideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;

	@Override
	public List<Video> requestVideo(Integer channelId)
	{
		
		return videoMapper.selectSubscribeVideo(channelId);
	}

}

