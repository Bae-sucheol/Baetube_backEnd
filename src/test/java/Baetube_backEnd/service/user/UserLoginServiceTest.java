package Baetube_backEnd.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;
import Baetube_backEnd.service.user.UserLoginService;

public class UserLoginServiceTest
{
	@InjectMocks
	private UserLoginService userLoginService;
	
	@Mock
	private UserMapper userMapper;
	
	private String newEmail = "new@naver.com";
	private String dupleEmail = "duple@naver.com";
	private String duplePassword = "1234";
	private String dupleWrongPassowrd = "12345";
	private User newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
			"1234", "1234", new Timestamp(System.currentTimeMillis()));
	private User dupleUser = new User(1, dupleEmail, duplePassword, "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
			"1234", "1234", new Timestamp(System.currentTimeMillis()));
	User dupleUserWrongPassword = new User(1, dupleEmail, dupleWrongPassowrd, "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
			"1234", "1234", new Timestamp(System.currentTimeMillis()));
	
	@Before
	public void setUp()
	{
		userLoginService = new UserLoginService();
		userLoginService.setUserMapper(userMapper);
		MockitoAnnotations.initMocks(this);
		
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);
		when(userMapper.selectByEmail(dupleEmail)).thenReturn(dupleUser);
	}
	
	@Test
	public void correctTest()
	{
		dupleUser = userLoginService.login(dupleUser);

		assertEquals(dupleEmail, dupleUser.getEmail());
		assertEquals(duplePassword, dupleUser.getPassword());
	}
	
	@Test(expected=WrongIdPasswordException.class)
	public void userNullTest()
	{
		newUser = userLoginService.login(newUser);
	}
	
	@Test(expected=WrongIdPasswordException.class)
	public void wrongPasswordTest()
	{
		dupleUserWrongPassword = userLoginService.login(dupleUserWrongPassword);
	}
}
