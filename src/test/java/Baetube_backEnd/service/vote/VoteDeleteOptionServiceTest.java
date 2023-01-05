package Baetube_backEnd.service.vote;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteDeleteOptionServiceTest
{
	@InjectMocks
	private VoteDeleteOptionService voteDeleteOptionService;
	
	@Mock
	private VoteMapper voteMapper;
	
	private Vote requestVote;
	
	@Before
	public void setUp()
	{
		voteDeleteOptionService = new VoteDeleteOptionService();
		voteDeleteOptionService.setVoteMapper(voteMapper);
		
		MockitoAnnotations.initMocks(this);
		
		requestVote = new Vote(1, 1, "test", 0);
	}
	
	@Test
	public void correctTest()
	{
		when(voteMapper.selectVoteOption(1)).thenReturn(requestVote);
		
		assertEquals(true, voteDeleteOptionService.deleteOption(requestVote));
		verify(voteMapper, atLeastOnce()).selectVoteOption(1);
	}
	
	@Test(expected = NullVoteException.class)
	public void wrongTest()
	{
		when(voteMapper.selectVoteOption(1)).thenReturn(null);
		
		assertEquals(true, voteDeleteOptionService.deleteOption(requestVote));
		verify(voteMapper, atLeastOnce()).selectVoteOption(1);
	}
}
