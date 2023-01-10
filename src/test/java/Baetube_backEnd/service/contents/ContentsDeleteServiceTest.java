package Baetube_backEnd.service.contents;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.mapper.ContentsMapper;

public class ContentsDeleteServiceTest
{
	@InjectMocks
	private ContentsDeleteService contentsDeleteService;
	@Mock
	private ContentsMapper contentsMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Contents contents = new Contents(1L, 0);
		
		assertEquals(true, contentsDeleteService.deleteContents(contents));
		verify(contentsMapper, atLeastOnce());
	}
	
}
