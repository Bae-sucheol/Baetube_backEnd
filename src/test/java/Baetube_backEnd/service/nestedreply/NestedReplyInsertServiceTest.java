package Baetube_backEnd.service.nestedreply;

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

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.mapper.ReplyMapper;

public class NestedReplyInsertServiceTest
{
	@InjectMocks
	private NestedReplyInsertService nestedReplyInsertService;
	
	@Mock
	private NestedReplyMapper nestedReplyMapper;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Mock
	private ContentsMapper contentsMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		NestedReply nestedReply = new NestedReply(1, 1, 2L, 1, "test", null, "test", "test", 1L);
		Reply reply = new Reply(1, 1L, 1L, 1, "test", 0, 0, null, 0, "test", "test");
		
		when(replyMapper.selectByReplyId(1)).thenReturn(reply);
		
		assertEquals(false, nestedReplyInsertService.insertNestedReply(nestedReply).isEmpty());
		verify(nestedReplyMapper, atLeastOnce()).insert(nestedReply);
		verify(replyMapper, atLeastOnce()).selectByReplyId(any());
		verify(contentsMapper, atLeastOnce()).selectFCMTokenByContentsId(any(), any(), any(), any());
	}
}
