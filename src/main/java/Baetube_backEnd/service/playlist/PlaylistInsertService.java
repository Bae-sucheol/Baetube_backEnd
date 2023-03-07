package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	public Integer insert(Playlist request)
	{
		playlistMapper.insert(request);
		
		return request.getPlaylistId();
	}
}
