package Baetube_backEnd.service.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoUpdateService
{
	@Autowired
	private VideoMapper videoMapper;

	public void setVideoMapper(VideoMapper videoMapper)
	{
		this.videoMapper = videoMapper;
	}
	
	@Transactional
	public boolean update(Video request)
	{
		Video video = videoMapper.selectByVideoId(request.getVideoId());
		
		// ������ ������Ʈ�� ä�� �����ڰ� ���� �����ϹǷ� �� �ٸ� ���� ���� �������� �ʴ´�.
		// ���� �ش� ������ ��ü�� null���� �ƴ��� �ľ��� �ʿ�� ���� �� ����. 
		
		// Ŭ���̾�Ʈ �ܿ��� �ش� ������ �����Ͱ� �����Ǿ����� �ľ��ϰ� ��û�� ������ 
		// �ش� ���������� �� �����͸� ���Ͽ� �ٸ� �κи� ������Ʈ �ϹǷ� �ٸ� ������ �ʿ� ����.
		videoMapper.update(video, request);
		
		return true;
	}
}
