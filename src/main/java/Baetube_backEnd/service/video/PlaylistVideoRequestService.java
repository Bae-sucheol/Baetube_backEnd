package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class PlaylistVideoRequestService implements VideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	
	public void setVideoMapper(VideoMapper videoMapper)
	{
		this.videoMapper = videoMapper;
	}

	@Override
	public List<Video> requestVideo(Integer playlistId)
	{
	
		return videoMapper.selectPlaylistVideo(playlistId);
	}

}
