package Baetube_backEnd.service.vote;

import static org.junit.Assert.assertEquals;
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
import Baetube_backEnd.mapper.VoteMapper;

public class VoteSelectOptionServiceTest
{
	@InjectMocks
	private VoteSelectOptionService voteSelectOptionService;
	
	@Mock
	private VoteMapper voteMapper;
	
	private Vote requestVoteA;
	private Vote requestVoteB;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		requestVoteA = new Vote(1, 1, "testa", 0);
		requestVoteB = new Vote(1, 2, "testb", 0);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Vote> requestList = new ArrayList<>();
		requestList.add(requestVoteA);
		requestList.add(requestVoteB);
		
		when(voteMapper.selectVoteOptions(0)).thenReturn(null);
		when(voteMapper.selectVoteOptions(1)).thenReturn(requestList);
		
		assertEquals(requestList, voteSelectOptionService.select(1));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
		
		assertEquals(null, voteSelectOptionService.select(0));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(0);
	}
}
