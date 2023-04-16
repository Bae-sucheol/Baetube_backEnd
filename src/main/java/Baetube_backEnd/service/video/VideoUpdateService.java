package Baetube_backEnd.service.video;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class VideoUpdateService
{
	@Autowired
	private VideoMapper videoMapper;
	@Autowired
	private FileUploadService fileUploadService;
	
	@Transactional
	public HashMap<String, String> update(Video request) throws IOException
	{
		Video video = videoMapper.selectByVideoId(request.getVideoId());
		
		if(video == null)
		{
			throw new NullVideoException();
		}
		
		HashMap<String, String> isChangedImage = new HashMap<>();
		
		if(!video.getThumbnail().equals(request.getThumbnail()))
		{
			fileUploadService.deleteImage("image", "thumbnail", video.getThumbnail());
			
			String thumbnail = UUIDUtil.createUUID();
			isChangedImage.put("thumbnail", thumbnail);
			request.setThumbnail(thumbnail);
		}
		
		videoMapper.update(video, request);
		
		return isChangedImage;
	}
}
