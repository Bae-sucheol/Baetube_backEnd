package Baetube_backEnd.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.ChangePasswordRequest;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class ChangePasswordServiceTest
{
	@InjectMocks
	private ChangePasswordService changePasswordService;
	
	@Mock
	private UserMapper userMapper;
	
	private String newEmail;
	private String dupleEmail;
	private String duplePassword;
	private String dupleWrongPassowrd;
	private User newUser;
	private User dupleUser;
	private User dupleUserWrongPassword;
	
	@Before
	public void setUp()
	{
		changePasswordService = new ChangePasswordService();
		changePasswordService.setUserMapper(userMapper);
		MockitoAnnotations.initMocks(this);
		
		newEmail = "new@naver.com";
		dupleEmail = "duple@naver.com";
		duplePassword = "1234";
		dupleWrongPassowrd = "12345";
		
		newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		dupleUser = new User(1, dupleEmail, duplePassword, "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		dupleUserWrongPassword = new User(1, dupleEmail, dupleWrongPassowrd, "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);
		when(userMapper.selectByEmail(dupleEmail)).thenReturn(dupleUser);
	}
	
	@Test
	public void correctTest()
	{
		ChangePasswordRequest correctRequest = new ChangePasswordRequest(dupleEmail, duplePassword, "12345678");

		assertEquals(true, changePasswordService.changePassword(correctRequest));
	}
	
	@Test(expected=WrongIdPasswordException.class)
	public void userNullTest()
	{
		ChangePasswordRequest userNullRequest = new ChangePasswordRequest(newEmail, "1234", "12345678");
		
		changePasswordService.changePassword(userNullRequest);
	}
	
	@Test(expected=WrongIdPasswordException.class)
	public void wrongPasswordTest()
	{
		ChangePasswordRequest wrongPasswordRequest = new ChangePasswordRequest(dupleEmail, dupleWrongPassowrd, "12345678");
		
		changePasswordService.changePassword(wrongPasswordRequest);
	}
}
