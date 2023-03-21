package Baetube_backEnd.service.playlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteLikeVideoService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	
	@Transactional
	public void deleteLikeVideo(Video request)
	{
		PlaylistItem playlistItem = playlistMapper.selectLikePlaylist(request.getChannelId(), request.getVideoId());
		
		// 이미 동영상이 존재한다면 좋아요를 한번 더 누른 것으로 간주하고 해당 튜플(레코드)을 삭제한다.
		if(playlistItem != null)
		{
			playlistMapper.deleteItem(playlistItem);
		}
	}
}
