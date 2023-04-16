package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistSelectService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public Playlist selectPlaylistData(Integer request) throws NullPlaylistException
	{
		Playlist playlist = playlistMapper.selectByPlaylistId(request);
		
		if(playlist == null)
		{
			throw new NullPlaylistException();
		}
		
		return playlist;
	}
}
