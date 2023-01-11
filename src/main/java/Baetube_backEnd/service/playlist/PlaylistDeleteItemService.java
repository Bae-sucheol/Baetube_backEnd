package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteItemService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public boolean deleteItem(PlaylistItem request)
	{
		PlaylistItem playlistItem = playlistMapper.selectPlaylistItem(request.getPlaylistId(), request.getVideoId());
		
		if(playlistItem == null)
		{
			throw new NullPlaylistItemException();
		}
		
		playlistMapper.deleteItem(request);
		
		return true;
	}
}
