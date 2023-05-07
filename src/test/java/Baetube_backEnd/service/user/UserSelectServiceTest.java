package Baetube_backEnd.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.mapper.UserMapper;

public class UserSelectServiceTest
{
	@InjectMocks
	private UserSelectService userSelectService;
	
	@Mock
	private UserMapper userMapper;
	
	private String newEmail;
	private User newUser;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		newEmail = "new@naver.com";
		newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
	}
	
	@Test
	public void correctTest()
	{
		when(userMapper.selectByEmail(newEmail)).thenReturn(newUser);
		assertEquals(newUser, userSelectService.selectUserByEmail(newEmail));
		verify(userMapper, atLeastOnce()).selectByEmail(any());
	}
	
	@Test(expected=NullUserException.class)
	public void wrongTest()
	{
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);

		assertEquals(newUser, userSelectService.selectUserByEmail(newEmail));
		verify(userMapper, atLeastOnce()).selectByEmail(any());
	}
	
}
