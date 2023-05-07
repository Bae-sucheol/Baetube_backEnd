package Baetube_backEnd.service.rate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.exception.NullRateResultException;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.RateMapper;

public class RateServiceTest
{
	@InjectMocks
	private RateService rateService;
	
	@Mock
	private RateMapper rateMapper;
	
	@Mock
	private ContentsMapper contentsMapper;
	
	private Rate correctRate;
	private Rate wrongRate;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		correctRate = new Rate(1L, 0, 1);
		wrongRate = new Rate(1L, 0, null);
	}
	
	@Test
	public void correctTest()
	{
		Contents contents = new Contents();
		
		when(contentsMapper.selectByContentsId(1L)).thenReturn(contents);
		
		assertEquals(contents, rateService.rate(correctRate));
		
		verify(rateMapper, atLeastOnce()).insert(any());
	}
	
	@Test(expected = NullRateResultException.class)
	public void wrongTest()
	{
		Contents contents = new Contents();
		
		assertEquals(contents, rateService.rate(wrongRate));
	}
}
