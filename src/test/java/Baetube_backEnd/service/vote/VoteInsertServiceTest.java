package Baetube_backEnd.service.vote;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertServiceTest
{
	@InjectMocks
	private VoteInsertService voteInsertService;
	
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
		Vote requestVote = new Vote(1, 1, "test", 0);
		
		assertEquals(false, voteInsertService.insert(requestVote).isEmpty());
		verify(voteMapper, atLeastOnce()).insertVote(requestVote);
	}
}
