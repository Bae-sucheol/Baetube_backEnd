package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertLikeVideoService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public void insertLikeVideo(Video request)
	{
		PlaylistItem playlistItem = playlistMapper.selectLikePlaylist(request.getChannelId(), request.getVideoId());
		
		// �̹� �������� �����Ѵٸ� ���ƿ並 �ѹ� �� ���� ������ �����ϰ� �ش� Ʃ��(���ڵ�)�� �����Ѵ�.
		if(playlistItem != null)
		{
			playlistMapper.deleteItem(playlistItem);
		}
		else 
		{
			playlistMapper.insertLikeVideo(request.getChannelId(), request.getVideoId());
		}
	}
	
}