package Baetube_backEnd.service.playlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistChannelService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	public List<Playlist> select(Integer channelId)
	{
		List<Playlist> playlistList = playlistMapper.selectByChannelId(channelId);
		
		if(playlistList == null)
		{
			throw new NullPlaylistException();
		}
		
		return playlistList;
	}
}
