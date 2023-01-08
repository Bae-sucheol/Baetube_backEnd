package Baetube_backEnd.service.nestedreply;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.service.nestedreply.NestedReplySelectService;

public class NestedReplySelectServiceTest
{
	@InjectMocks
	private NestedReplySelectService nestedReplySelectService;
	
	@Mock
	private NestedReplyMapper nestedReplyMapper;
	
	@Before
	public void setUp()
	{
		nestedReplySelectService = new NestedReplySelectService();
		nestedReplySelectService.setNestedReplyMapper(nestedReplyMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<NestedReply> nestedReplyList = new ArrayList<>();
		nestedReplyList.add(any());
		
		when(nestedReplyMapper.selectByReplyId(1)).thenReturn(nestedReplyList);
		
		assertEquals(nestedReplyList, nestedReplySelectService.selectNestedReply(1));
	}
	
	@Test(expected = NullReplyException.class)
	public void wrongTest()
	{
		when(nestedReplyMapper.selectByReplyId(1)).thenReturn(null);
		
		assertEquals(null, nestedReplySelectService.selectNestedReply(1));
	}
}
