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
		
		// 동영상 업데이트는 채널 관리자가 직접 관리하므로 별 다른 이유 없이 삭제되지 않는다.
		// 따라서 해당 동영상 객체가 null인지 아닌지 파악할 필요는 없을 것 같다. 
		
		// 클라이언트 단에서 해당 동영상 데이터가 수정되었는지 파악하고 요청을 보내며 
		// 해당 쿼리문에서 두 데이터를 비교하여 다른 부분만 업데이트 하므로 다른 검증은 필요 없다.
		videoMapper.update(video, request);
		
		return true;
	}
}
