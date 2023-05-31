package Baetube_backEnd.service.contents;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullContentsException;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class ContentsDeleteService
{
	@Autowired
	private ContentsMapper contentsMapper;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private VideoMapper videoMapper;
	
	@Transactional
	public boolean deleteContents(Contents request) throws IOException
	{
		Contents contents = contentsMapper.selectByContentsId(request.getContentsId());
		
		if(contents == null)
		{
			throw new NullContentsException();
		}
		
		Video video = videoMapper.selectByContentsId(request.getContentsId());
		
		contentsMapper.delete(request.getContentsId(), request.getType());
		
		fileUploadService.deleteVideo(video.getUrl());
		
		return true;
	}
}
