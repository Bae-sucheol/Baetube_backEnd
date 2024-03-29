package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoViewService
{
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private HistoryMapper historyMapper;
	
	@Transactional
	public Video selectVideo(Integer userId, Integer videoId)
	{
		/* 
		 * 먼저 해당 동영상의 조회수를 증가시킨다.
		 * 시청하려는 순간 비디오가 삭제되거나 정보가 변하는 일이 있을 수 있으므로.
	 	 * 비디오를 한번 더 조회하여 반환한다.
	 	*/
		
		videoMapper.updateViews(videoId, 1);
		
		historyMapper.insert(userId, videoId);
		
		Video video = videoMapper.selectByVideoId(videoId);
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		// 문제없다면 리턴한다.
		
		return video;
	}
}
