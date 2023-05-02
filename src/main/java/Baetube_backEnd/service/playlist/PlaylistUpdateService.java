package Baetube_backEnd.service.playlist;

import java.io.IOException;
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
	public HashMap<String, String> update(Playlist request) throws IOException
	{
		Playlist playlist = playlistMapper.selectByPlaylistId(request.getPlaylistId());
		
		if(playlist == null)
		{
			throw new NullPlaylistException();
		}
		
		HashMap<String, String> isChangedImage = new HashMap<>();
		
		// 현재 썸네일과 업데이트할 썸네일이 다르다면.
		if(!playlist.getThumbnail().equals(request.getThumbnail()))
		{
			// 현재 썸네일을 통해 동영상을 조회한다.
			Video video = videoMapper.selectByThumbnail(playlist.getThumbnail());
			
			// 같은 썸네일을 사용중인 동영상이 존재하지 않는다면 삭제해도 무방할 것.
			if(video == null)
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
