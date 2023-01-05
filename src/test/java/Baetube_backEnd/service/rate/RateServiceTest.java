package Baetube_backEnd.service.rate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;

import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.mapper.RateMapper;

public class RateServiceTest
{
	@InjectMocks
	private RateService rateService;
	
	@Mock
	private RateMapper rateMapper;
	
	@Before
	public void setUp()
	{
		rateService = new RateService();
		rateService.setRateMapper(rateMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * 1 = 정상 삽입
	 * 2 = 이미 있어서 대채
	 * 3 = 좋아요 -> 싫어요
	 * 4 = 싫어요 -> 좋아요
	 */
	
	@Test
	public void correctTest()
	{
		Rate rate = new Rate(1L, 1, 1, null);
		
		rateMapper.insert(rate);
		
		rate.setResult(1);
		
		assertTrue(1 == rateService.rate(rate));
		verify(rateMapper, atLeastOnce()).insert(rate);
	}
	
	@Test(expected = NullRateResultException.class)
	public void wrongTest()
	{
		Rate rate = new Rate(1L, 1, 1, null);
		
		assertTrue(1 == rateService.rate(rate));
		verify(rateMapper, atLeastOnce()).insert(rate);
	}
}
