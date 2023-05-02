package Baetube_backEnd.service.nestedreply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.service.nestedreply.NestedReplyUpdateService;

public class NestedReplyUpdateServiceTest
{
	@InjectMocks
	private NestedReplyUpdateService nestedReplyUpdateService;
	
	@Mock
	private NestedReplyMapper nestedReplyMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		NestedReply nestedReply = new NestedReply(1, 1, 2L, 1, "test", null, "test", "test", 1L);
		
		when(nestedReplyMapper.selectByNestedReplyId(1)).thenReturn(nestedReply);
		
		assertEquals(true, nestedReplyUpdateService.updateNestedReply(nestedReply));
		verify(nestedReplyMapper, atLeastOnce()).updateComment(nestedReply.getNestedReplyId(), nestedReply.getComment());
	}
	
	@Test(expected = NullReplyException.class)
	public void wrongTest()
	{
		NestedReply nestedReply = new NestedReply(1, 1, 2L, 1, "test", null, "test", "test", 1L);
		
		when(nestedReplyMapper.selectByNestedReplyId(1)).thenReturn(null);
		
		assertEquals(true, nestedReplyUpdateService.updateNestedReply(nestedReply));
		verify(nestedReplyMapper, atLeastOnce()).updateComment(nestedReply.getNestedReplyId(), nestedReply.getComment());
	}
	
}
