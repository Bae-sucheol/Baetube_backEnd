package Baetube_backEnd.service.playlist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertItemService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public boolean insertItem(List<PlaylistItem> request)
	{
		Integer count = playlistMapper.selectPlaylistItemCount(request);
		
		if(count != 0)
		{
			throw new DuplicatePlaylistItemException();
		}
		
		playlistMapper.insertItem(request);
		playlistMapper.updateVideoCount(request.get(0).getPlaylistId(), request.size());
		
		return true;
	}
	
	@Transactional
	public boolean insertLater(Integer videoId, Integer channelId) throws DuplicatePlaylistItemException
	{
		Playlist laterPlaylist = playlistMapper.selectChannelLaterPlaylist(channelId);
		
		System.out.println("laterPlaylistId : " + laterPlaylist.getPlaylistId());
		
		PlaylistItem item = playlistMapper.selectPlaylistItem(laterPlaylist.getPlaylistId(), videoId);
		
		System.out.println("videoId : " + videoId);
		
		if(item != null)
		{
			throw new DuplicatePlaylistItemException();
		}
		
		List<PlaylistItem> insertItems = new ArrayList<PlaylistItem>();
		insertItems.add(new PlaylistItem(laterPlaylist.getPlaylistId(), videoId));
		
		insertItem(insertItems);
		
		return true;
	}
}
