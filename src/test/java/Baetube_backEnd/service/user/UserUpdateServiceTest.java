package Baetube_backEnd.service.user;

import static org.junit.Assert.assertEquals;
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

public class UserUpdateServiceTest
{
	@InjectMocks
	private UserUpdateService userUpdateService;
	
	@Mock
	private UserMapper userMapper;
	
	private String newEmail;
	private String dupleEmail;
	private String duplePassword;
	private User newUser;
	private User dupleUser;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		newEmail = "new@naver.com";
		dupleEmail = "duple@naver.com";
		duplePassword = "1234";
		
		newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		dupleUser = new User(1, dupleEmail, duplePassword, "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);
		when(userMapper.selectByEmail(dupleEmail)).thenReturn(dupleUser);
	}
	
	@Test
	public void correctTest()
	{
		assertEquals(true, userUpdateService.update(dupleUser));
	}
	
	@Test(expected=NullUserException.class)
	public void userNullTest()
	{
		userUpdateService.update(newUser);
	}
	
}
