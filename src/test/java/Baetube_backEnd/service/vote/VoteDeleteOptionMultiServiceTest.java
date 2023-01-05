package Baetube_backEnd.service.vote;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.exception.NullVoteException;
import Baetube_backEnd.mapper.VoteMapper;

public class VoteDeleteOptionMultiServiceTest
{
	@InjectMocks
	private VoteDeleteOptionMultiService voteDeleteOptionMultiService;
	
	@Mock
	private VoteMapper voteMapper;
	
	private ArrayList<Vote> requestList;
	private Vote requestVoteA;
	private Vote requestVoteB;
	private Vote WrongVote;
	
	@Before
	public void setUp()
	{
		voteDeleteOptionMultiService = new VoteDeleteOptionMultiService();
		voteDeleteOptionMultiService.setVoteMapper(voteMapper);
		
		MockitoAnnotations.initMocks(this);
		
		requestVoteA = new Vote(1, 1, "testa", 0);
		requestVoteB = new Vote(1, 2, "testb", 0);
		WrongVote = new Vote(0, 0, "testwrong", 0);
		
		requestList = new ArrayList<>();
		requestList.add(requestVoteA);
		requestList.add(requestVoteB);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Vote> voteList = new ArrayList<>();
		voteList.add(requestVoteA);
		voteList.add(WrongVote);
		voteList.add(requestVoteB);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(voteList);
		
		assertEquals(true, voteDeleteOptionMultiService.deleteOptionMulti(requestList));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
	
	@Test(expected = NullVoteException.class)
	public void wrongTest()
	{
		ArrayList<Vote> voteList = new ArrayList<>();
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		voteList.add(WrongVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(voteList);
		
		assertEquals(true, voteDeleteOptionMultiService.deleteOptionMulti(requestList));
		verify(voteMapper, atLeastOnce()).selectVoteOptions(1);
	}
}
