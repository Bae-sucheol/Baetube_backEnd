package Baetube_backEnd.service.playlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertItemMultiService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public boolean insertItemMulti(List<PlaylistItem> request)
	{
		Integer count = playlistMapper.selectPlaylistItemCount(request);
		
		if(count != 0)
		{
			throw new DuplicatePlaylistItemException();
		}
		
		playlistMapper.insertItem(request);
		playlistMapper.updateVideoCountMulti(request, 1);
		
		return true;
	}
}
