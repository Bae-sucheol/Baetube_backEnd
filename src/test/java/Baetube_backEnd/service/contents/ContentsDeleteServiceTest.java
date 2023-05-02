package Baetube_backEnd.service.contents;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.exception.NullContentsException;
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
		
		when(contentsMapper.selectByContentsId(1L)).thenReturn(contents);
		
		assertEquals(true, contentsDeleteService.deleteContents(contents));
		
		verify(contentsMapper, atLeastOnce()).delete(any(), any());
	}
	
	@Test(expected = NullContentsException.class)
	public void wrongTest()
	{
		Contents contents = new Contents(1L, 0);
		
		when(contentsMapper.selectByContentsId(1L)).thenReturn(null);
		
		assertEquals(true, contentsDeleteService.deleteContents(contents));
		
		verify(contentsMapper, atLeastOnce()).delete(any(), any());
	}
	
}
