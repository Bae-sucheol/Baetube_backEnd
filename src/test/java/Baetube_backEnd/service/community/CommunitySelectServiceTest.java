package Baetube_backEnd.service.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
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

public class CommunitySelectServiceTest
{
	@InjectMocks
	private CommunitySelectService communitySelectService;
	@Mock
	private CommunityMapper communityMapper;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctSelectCommunityTest() throws IOException
	{	
		Community community = new Community();
		community.setCommunityId(1);
		
		when(communityMapper.selectByCommunityId(1)).thenReturn(community);
		
		assertEquals(community, communitySelectService.selectCommunity(1));
	}
	
	@Test
	public void correctSelectSubscribersCommunityTest()
	{	
		List<Community> communityList = new ArrayList<>();
		communityList.add(any());
		
		when(communityMapper.selectSubscribersCommunity(1)).thenReturn(communityList);
		
		assertEquals(communityList, communitySelectService.selectSubscribersCommunity(1));
	}
	
	@Test(expected = NullCommunityException.class)
	public void wrongSelectCommunityTest() throws IOException
	{
		Community community = new Community();
		community.setCommunityId(1);
		
		when(communityMapper.selectByCommunityId(1)).thenReturn(null);
		
		assertEquals(community, communitySelectService.selectCommunity(1));
	}
	
	@Test(expected = NullCommunityException.class)
	public void wrongSelectSubscribersCommunityTest()
	{
		List<Community> communityList = new ArrayList<>();
		communityList.add(any());
		
		when(communityMapper.selectSubscribersCommunity(1)).thenReturn(null);
		
		assertEquals(communityList, communitySelectService.selectSubscribersCommunity(1));
	}
}
