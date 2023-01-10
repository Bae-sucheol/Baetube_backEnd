package Baetube_backEnd.service.rate;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.mapper.RateMapper;

public class RateService
{	
	@Autowired
	private RateMapper rateMapper;
	
	public Integer rate(Rate request)
	{
		rateMapper.insert(request);
		
		if(request.getResult() == null)
		{
			throw new NullRateResultException();
		}
		
		return request.getResult();
	}
}
