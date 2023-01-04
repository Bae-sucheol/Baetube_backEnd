package Baetube_backEnd.service.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityChannelVisitServiceTest
{
	@InjectMocks
	private CommunityChannelVisitService communityChannelVisitService;
	@Mock
	private CommunityMapper communityMapper;
	
	@Before
	public void setUp()
	{
		communityChannelVisitService = new CommunityChannelVisitService();
		communityChannelVisitService.setCommunityMapper(communityMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Community> communityList = new ArrayList<>();
		communityList.add(any());
		
		when(communityMapper.selectByChannel(1)).thenReturn(communityList);
		
		assertEquals(communityList, communityChannelVisitService.selectCommunity(1));
	}
	
	@Test(expected = NullCommunityException.class)
	public void wrongTest()
	{
		when(communityMapper.selectByChannel(1)).thenReturn(null);
		
		assertEquals(null, communityChannelVisitService.selectCommunity(1));
	}
}
