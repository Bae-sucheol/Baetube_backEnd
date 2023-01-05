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
import Baetube_backEnd.exception.DuplicateVoteOptionException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteInsertOptionServiceTest
{
	@InjectMocks
	private VoteInsertOptionService voteInsertOptionService;
	
	@Mock
	private VoteMapper voteMapper;
	
	private ArrayList<Vote> requestList;
	private Vote requestVote;
	private Vote WrongVote;
	
	@Before
	public void setUp()
	{
		voteInsertOptionService = new VoteInsertOptionService();
		voteInsertOptionService.setVoteMapper(voteMapper);
		
		MockitoAnnotations.initMocks(this);
		
		requestVote = new Vote(1, 1, "test", 0);
		WrongVote = new Vote(0, 0, "testwrong", 0);
		
		requestList = new ArrayList<>();
		requestList.add(requestVote);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Vote> voteList = new ArrayList<>();
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(voteList);
		
		assertEquals(true, voteInsertOptionService.insertOption(requestVote));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
	
	@Test(expected = DuplicateVoteOptionException.class)
	public void wrongTest()
	{
		ArrayList<Vote> voteList = new ArrayList<>();
		voteList.add(requestVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(voteList);
		
		assertEquals(true, voteInsertOptionService.insertOption(requestVote));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
}
