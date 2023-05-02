package Baetube_backEnd.service.reply;

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

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyUpdateServiceTest
{
	@InjectMocks
	private ReplyUpdateService replyUpdateService;
	
	@Mock
	private ReplyMapper replyMapper;
	
	private Reply reply;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		reply = new Reply(1, 2L, 1L, 1, "test", 0, 0, null, 0, "test", null);
	}
	
	@Test
	public void correctTest()
	{
		when(replyMapper.selectByReplyId(1)).thenReturn(reply);
		
		assertEquals(true, replyUpdateService.updateReply(reply));
		verify(replyMapper, atLeastOnce()).updateComment(any(), any());
	}
	
	@Test(expected = NullReplyException.class)
	public void wrongTest()
	{
		when(replyMapper.selectByReplyId(1)).thenReturn(null);
		
		assertEquals(true, replyUpdateService.updateReply(reply));
		verify(replyMapper, atLeastOnce()).updateComment(any(), any());
	}
	
}
