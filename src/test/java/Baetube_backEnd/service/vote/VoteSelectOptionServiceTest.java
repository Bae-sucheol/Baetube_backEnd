package Baetube_backEnd.service.vote;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteSelectOptionServiceTest
{
	@InjectMocks
	private VoteSelectOptionService voteSelectOptionService;
	
	@Mock
	private VoteMapper voteMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Vote> requestList = new ArrayList<>();
		requestList.add(any());
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(requestList);
		
		assertEquals(requestList, voteSelectOptionService.select(1));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
	
	@Test(expected = NullVoteException.class)
	public void wrongTest()
	{
		ArrayList<Vote> requestList = new ArrayList<>();
		requestList.add(any());
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(null);
		
		assertEquals(requestList, voteSelectOptionService.select(1));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
}
