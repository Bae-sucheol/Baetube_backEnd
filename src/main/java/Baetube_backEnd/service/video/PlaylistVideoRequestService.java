package Baetube_backEnd.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class PlaylistVideoRequestService
{
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public List<Video> requestVideo(Integer playlistId)
	{
		Playlist playlist = playlistMapper.selectByPlaylistId(playlistId);
		
		if(playlist == null)
		{
			throw new NullPlaylistException();
		}
		
		List<Video> videoList = videoMapper.selectPlaylistVideo(playlistId);
		
		if(videoList == null)
		{
			throw new NullVideoException();
		}
		
		return videoList;
	}

	

}
