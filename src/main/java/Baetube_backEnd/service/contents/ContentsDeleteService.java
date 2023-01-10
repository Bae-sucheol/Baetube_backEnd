package Baetube_backEnd.service.contents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.exception.NullContentsException;
import Baetube_backEnd.mapper.ContentsMapper;

public class ContentsDeleteService
{
	@Autowired
	private ContentsMapper contentsMapper;
	
	@Transactional
	public boolean deleteContents(Contents request)
	{
		Contents contents = contentsMapper.selectByContentsId(request.getContentsId());
		
		if(contents == null)
		{
			throw new NullContentsException();
		}
		
		contentsMapper.delete(request.getContentsId(), request.getType());
		
		return true;
	}
}
