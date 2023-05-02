package Baetube_backEnd.service.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.RateMapper;

public class RateService
{	
	@Autowired
	private RateMapper rateMapper;
	@Autowired
	private ContentsMapper contentsMapper;
	
	@Transactional
	public Contents rate(Rate request)
	{
		rateMapper.insert(request);
		
		if(request.getResult() == null)
		{
			throw new NullRateResultException();
		}
		
		Contents contents = contentsMapper.selectByContentsId(request.getContentsId());
		
		return contents;
	}
}
