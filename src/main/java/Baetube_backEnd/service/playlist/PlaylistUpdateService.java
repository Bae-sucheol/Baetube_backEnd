package Baetube_backEnd.service.playlist;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class PlaylistUpdateService
{
	@Autowired
	private PlaylistMapper playlistMapper;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private VideoMapper videoMapper;
	
	
	@Transactional
	public HashMap<String, String> update(Playlist request)
	{
		Playlist playlist = playlistMapper.selectByPlaylistId(request.getPlaylistId());
		
		if(playlist == null)
		{
			throw new NullPlaylistException();
		}
		
		HashMap<String, String> isChangedImage = new HashMap<>();
		
		if(!playlist.getThumbnail().equals(request.getThumbnail()))
		{
			Video video = videoMapper.selectByThumbnail(playlist.getThumbnail());
			
			if(video != null)
			{
				fileUploadService.deleteImage("image", "thumbnail", playlist.getThumbnail());
			}
			
			String thumbnail = UUIDUtil.createUUID();
			isChangedImage.put("thumbnail", thumbnail);
			request.setThumbnail(thumbnail);
		}
		
		playlistMapper.update(playlist, request);
		
		return isChangedImage;
	}
}
