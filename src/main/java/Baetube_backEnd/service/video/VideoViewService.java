package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoViewService
{
	@Autowired
	private VideoMapper videoMapper;

	public void setVideoMapper(VideoMapper videoMapper)
	{
		this.videoMapper = videoMapper;
	}
	
	@Transactional
	public Video selectVideo(Integer videoId)
	{
		/* 
		 * ���� �ش� �������� ��ȸ���� ������Ų��.
		 * ��û�Ϸ��� ���� ������ �����ǰų� ������ ���ϴ� ���� ���� �� �����Ƿ�.
	 	 * ������ �ѹ� �� ��ȸ�Ͽ� ��ȯ�Ѵ�.
	 	*/
		
		videoMapper.updateViews(videoId, 1);
		
		Video video = videoMapper.selectByVideoId(videoId);
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		// �������ٸ� �����Ѵ�.
		
		return video;
	}
}
