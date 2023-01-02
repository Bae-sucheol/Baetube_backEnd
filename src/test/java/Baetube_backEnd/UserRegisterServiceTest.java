package Baetube_backEnd;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
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
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.mapper.UserMapper;
import Baetube_backEnd.service.UserRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class UserRegisterServiceTest
{
	@InjectMocks
	private UserRegisterService userRegisterService;
	
	@Mock
	private UserMapper userMapper;
	
	@Before
	public void setUp()
	{
		userRegisterService = new UserRegisterService(userMapper);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test(expected=DuplicateUserException.class)
	public void registTest()
	{
		String newEmail = "new@naver.com";
		String dupleEmail = "duple@naver.com";
		
		User newUser = new User(0, newEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		
		User dupleUser = new User(1, dupleEmail, "1234", "1234", 1, new Timestamp(System.currentTimeMillis()), "1234",
				"1234", "1234", new Timestamp(System.currentTimeMillis()));
		
		when(userMapper.selectByEmail(newEmail)).thenReturn(null);
		when(userMapper.selectByEmail(dupleEmail)).thenReturn(dupleUser);

		
		assertEquals((Integer)0, userRegisterService.regist(newUser));
		assertEquals((Integer)0, userRegisterService.regist(dupleUser));
	}
}
