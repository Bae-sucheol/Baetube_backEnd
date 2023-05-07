package Baetube_backEnd.service.community;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class CommunityUpdateServiceTest
{
	@InjectMocks
	private CommunityUpdateService communityUpdateService;
	@Mock
	private CommunityMapper communityMapper;
	@Mock
	private FileUploadService fileUploadService;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctSelectCommunityTest() throws IOException
	{	
		Community communityOld = new Community(1, 1L, 1, 0, 0,
				0, "test", "test", null, null, "test", null, null, 0, 0, "test", "test");
		
		Community communityNew = new Community(1, 1L, 1, 0, 0,
				0, "test2", "test", null, null, "test", null, null, 0, 0, "test", "test");
		
		Community communityNewSame = new Community(1, 1L, 1, 0, 0,
				0, "test", "test", null, null, "test", null, null, 0, 0, "test", "test");
		
		when(communityMapper.selectByCommunityId(1)).thenReturn(communityOld);
		
		assertFalse(communityUpdateService.updateCommunity(communityNew).isEmpty());
		
		verify(fileUploadService, atLeastOnce()).deleteImage(any(), any(), any());
		verify(communityMapper, atLeastOnce()).update(any(), any());
		
		assertTrue(communityUpdateService.updateCommunity(communityNewSame).isEmpty());
	}
	
	@Test(expected = NullCommunityException.class)
	public void wrongSelectCommunityTest() throws IOException
	{
		Community community = new Community();
		community.setCommunityId(1);
		
		when(communityMapper.selectByCommunityId(1)).thenReturn(null);
		
		assertFalse(communityUpdateService.updateCommunity(community).isEmpty());
	}
	
}
