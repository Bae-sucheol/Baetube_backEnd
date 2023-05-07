package Baetube_backEnd.service.reply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.ReplyMapper;

public class ReplySelectServiceTest
{
	@InjectMocks
	private ReplySelectService replySelectService;
	
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
		ArrayList<Reply> replyList = new ArrayList<>();
		replyList.add(any());
		
		when(replyMapper.selectByContentsId(1L)).thenReturn(replyList);
		
		assertEquals(replyList, replySelectService.selectReply(1L));
	}
	
	@Test(expected = NullReplyException.class)
	public void wrongTest()
	{
		when(replyMapper.selectByContentsId(1L)).thenReturn(null);
		
		assertEquals(null, replySelectService.selectReply(1L));
	}
}
