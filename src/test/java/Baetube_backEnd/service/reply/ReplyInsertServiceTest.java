package Baetube_backEnd.service.reply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplyInsertServiceTest
{
	@InjectMocks
	private ReplyInsertService replyInsertService;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Reply reply = new Reply(1, 2L, 1L, 1, "test", 0, 0, null, 0, "test", null);
		
		assertEquals(true, replyInsertService.insertReply(reply));
		verify(replyMapper, atLeastOnce()).insert(reply);
	}
}
