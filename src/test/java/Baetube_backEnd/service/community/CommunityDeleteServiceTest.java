package Baetube_backEnd.service.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityDeleteServiceTest
{
	@InjectMocks
	private CommunityDeleteService communityDeleteService;
	@Mock
	private CommunityMapper communityMapper;
	
	@Before
	public void setUp()
	{
		communityDeleteService = new CommunityDeleteService();
		communityDeleteService.setCommunityMapper(communityMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Community community = new Community(1, null, null, null);
		
		when(communityMapper.selectByCommunityId(1)).thenReturn(community);
		
		assertEquals(true, communityDeleteService.deleteCommunity(1));
		verify(communityMapper, atLeastOnce()).selectByCommunityId(1);
	}
	
	@Test(expected = NullCommunityException.class)
	public void wrongTest()
	{
		when(communityMapper.selectByCommunityId(1)).thenReturn(null);
		
		assertEquals(true, communityDeleteService.deleteCommunity(1));
	}
}
