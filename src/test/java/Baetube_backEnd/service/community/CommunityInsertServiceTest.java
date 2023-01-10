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
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityInsertServiceTest
{
	@InjectMocks
	private CommunityInsertService communityInsertService;
	@Mock
	private CommunityMapper communityMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{	
		Community community = new Community(1, null, null, null);
		
		assertEquals(true, communityInsertService.insertCommunity(community));
		verify(communityMapper, atLeastOnce()).insert(community);
	}
}
