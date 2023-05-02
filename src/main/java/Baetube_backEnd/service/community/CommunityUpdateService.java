package Baetube_backEnd.service.community;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.UUIDUtil;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class CommunityUpdateService
{
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private FileUploadService fileUploadService;
	
	@Transactional
	public HashMap<String, String> updateCommunity(Community request) throws IOException
	{
		Community community = communityMapper.selectByCommunityId(request.getCommunityId());
		
		if(community == null)
		{
			throw new NullCommunityException();
		}
		
		HashMap<String, String> isChangedImage = new HashMap<>();
		
		if(!community.getImageUrl().equals(request.getImageUrl()))
		{
			fileUploadService.deleteImage("image", "community", community.getImageUrl());
			String imageUrl = UUIDUtil.createUUID();
			isChangedImage.put("imageUrl", imageUrl);
			request.setImageUrl(imageUrl);
		}
		
		communityMapper.update(community, request);
		
		return isChangedImage;
	}
}
