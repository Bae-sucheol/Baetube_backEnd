package Baetube_backEnd.service.nestedreply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.service.nestedreply.NestedReplyInsertService;

public class NestedReplyInsertServiceTest
{
	@InjectMocks
	private NestedReplyInsertService nestedReplyInsertService;
	
	@Mock
	private NestedReplyMapper nestedReplyMapper;
	
	@Before
	public void setUp()
	{
		nestedReplyInsertService = new NestedReplyInsertService();
		nestedReplyInsertService.setNestedReplyMapper(nestedReplyMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		NestedReply nestedReply = new NestedReply(1, 1, 2L, 1, "test", null, "test", "test", 1L);
		
		assertEquals(true, nestedReplyInsertService.insertNestedReply(nestedReply));
		verify(nestedReplyMapper, atLeastOnce()).insert(nestedReply);
	}
}
