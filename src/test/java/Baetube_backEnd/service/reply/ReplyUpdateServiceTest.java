package Baetube_backEnd.service.reply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyUpdateServiceTest
{
	@InjectMocks
	private ReplyUpdateService replyUpdateService;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Before
	public void setUp()
	{
		replyUpdateService = new ReplyUpdateService();
		replyUpdateService.setReplyMapper(replyMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Reply reply = new Reply(1, 2L, 1L, 1, "test", 0, 0, null, 0, "test", null);
		
		assertEquals(true, replyUpdateService.updateReply(reply));
		verify(replyMapper, atLeastOnce()).updateComment(reply.getReplyId(), reply.getComment());
	}
	
}
