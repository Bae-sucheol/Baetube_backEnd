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
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.mapper.UserMapper;

public class UserRegisterServiceTest
{
	@InjectMocks
	private UserRegisterService userRegisterService;
	
	@Mock
	private UserMapper userMapper;
	
	private String newEmail;
	private String dupleEmail;
	private User newUser;
	private User dupleUser;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		newEmail = "new@naver.com";
		dupleEmail = "duple@naver.com";
		newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		dupleUser = new User(1, dupleEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
	}
	
	@Test
	public void correctTest()
	{
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);
		
		userRegisterService.regist(newUser);
		verify(userMapper, atLeastOnce()).insert(any());
	}
	
	@Test(expected=DuplicateUserException.class)
	public void wrongTest()
	{
		when(userMapper.selectByEmail(dupleEmail)).thenReturn(dupleUser);

		assertEquals(dupleUser, userRegisterService.regist(dupleUser));
	}
	
}
