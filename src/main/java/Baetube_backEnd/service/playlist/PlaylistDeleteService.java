package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public boolean delete(Playlist request)
	{	
		Playlist playlist = playlistMapper.selectByPlaylistId(request.getPlaylistId());
		
		if(playlist == null)
		{
			throw new NullPlaylistException();
		}
		
		playlistMapper.delete(request);
		
		return true;
	}
}
