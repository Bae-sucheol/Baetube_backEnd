package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.dto.VideoViewRequest;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoViewService
{
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private HistoryMapper historyMapper;
	
	public void setVideoMapper(VideoMapper videoMapper)
	{
		this.videoMapper = videoMapper;
	}
	
	public void setHistoryMapper(HistoryMapper historyMapper)
	{
		this.historyMapper = historyMapper;
	}
	
	@Transactional
	public Video selectVideo(VideoViewRequest request)
	{
		/* 
		 * ���� �ش� �������� ��ȸ���� ������Ų��.
		 * ��û�Ϸ��� ���� ������ �����ǰų� ������ ���ϴ� ���� ���� �� �����Ƿ�.
	 	 * ������ �ѹ� �� ��ȸ�Ͽ� ��ȯ�Ѵ�.
	 	*/
		
		videoMapper.updateViews(request.getVideoId(), 1);
		
		historyMapper.insert(request.getUserId(), request.getVideoId());
		
		Video video = videoMapper.selectByVideoId(request.getVideoId());
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		// �������ٸ� �����Ѵ�.
		
		return video;
	}
}
