package Baetube_backEnd.service.contents;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.mapper.ContentsMapper;

public class ContentsDeleteService
{
	@Autowired
	private ContentsMapper contentsMapper;

	public void setContentsMapper(ContentsMapper contentsMapper)
	{
		this.contentsMapper = contentsMapper;
	}
	
	public boolean deleteContents(Contents request)
	{
		contentsMapper.delete(request.getContentsId(), request.getType());
		
		return true;
	}
}
