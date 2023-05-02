package Baetube_backEnd.service.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

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
		community.setCommunityId(1);
		community.setContentsId(1L);
		HashMap<String, String> result = communityInsertService.insertCommunity(community);
		
		assertEquals("1", result.get("contentsId"));
		assertEquals("1", result.get("communityId"));
		verify(communityMapper, atLeastOnce()).insert(any());
	}
}
