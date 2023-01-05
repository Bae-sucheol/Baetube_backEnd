package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertItemService
{
	@Autowired
	private PlaylistMapper playlistMapper;

	public void setPlaylistMapper(PlaylistMapper playlistMapper)
	{
		this.playlistMapper = playlistMapper;
	}
	
	@Transactional
	public boolean insertItem(PlaylistItem request)
	{
		PlaylistItem playlistItem = playlistMapper.selectPlaylistItem(request);
		
		if(playlistItem != null)
		{
			throw new DuplicatePlaylistItemException();
		}
		
		playlistMapper.insertItem(request);
		
		return true;
	}
}
