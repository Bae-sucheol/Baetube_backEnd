package Baetube_backEnd.service.channel;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class ChannelUpdateService
{
	@Autowired
	private ChannelMapper channelMapper;	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Transactional
	public HashMap<String, String> updateChannel(Channel request) throws IOException
	{
		Channel channel = channelMapper.select(request.getChannelId());
		
		if(channel == null)
		{
			throw new NullChannelException();
		}
		
		HashMap<String, String> isChangedImages = new HashMap<>();
		
		if(!channel.getArts().equals(request.getArts()))
		{
			fileUploadService.deleteImage("image", "arts", channel.getArts());
			String arts = UUIDUtil.createUUID();
			isChangedImages.put("arts", arts);
			request.setArts(arts);
		}
		
		if(!channel.getProfile().equals(request.getProfile()))
		{
			fileUploadService.deleteImage("image", "profile", channel.getArts());
			String profile = UUIDUtil.createUUID();
			isChangedImages.put("profile", profile);
			request.setProfile(profile);
		}
		
		channelMapper.update(channel, request);
		
		return isChangedImages;
	}
}
