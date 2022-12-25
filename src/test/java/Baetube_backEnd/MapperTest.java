package Baetube_backEnd;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.User;
import mapper.UserMapper;

/*
 * Controller.java - Service.java - Mapper.java - Mapper.xml ������ ����ϴ� ���� 
 * Controller.java - Service.java - DAO.java - Mapper.xml ������ ����ϴ� ��� ���� �ֱٿ� ���ǰ� �ִ� ����̶�� �Ѵ�.
 * ������ ������ ����ϸ� ���ں��� �����ϰ� ����� �� �ִ�. 
 * 1. DB���� Email�� ����Ͽ� �ش� Email�� ����ϴ� ������ ������ ��ȸ�ϴ� ���(��й�ȣ ã�� � ���.)
 * 2. DB�� �ۼ��� ������ ������ �����ϴ� �޼ҵ�
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class MapperTest
{
	@Mock
	private UserMapper userMapper;
	
	@Autowired
	private UserMapper userMapperReal;
	
	@Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void selectByEmailTest()
	{
		when(userMapper.selectByEmail("test@naver.com")).thenReturn(new User("1234", "test", "test@naver.com", "1", "1", "11111111111", "1", "1", true));
		
		User user = userMapper.selectByEmail("test@naver.com");
		
		assertEquals("1234", user.getPassword());
		assertEquals(true, user.getGender());
	}
	
	@Test
	public void insertTest()
	{
		User insert = new User("1234100", "test100", "test100@naver.com", "100", "100", "11111111111", "100", "100", true);
		
		userMapper.insert(insert);
		verify(userMapper).insert(insert);
	}
	
	
	@Test
	public void realTest()
	{
		userMapperReal.insert(new User("1234", "test", "test@naver.com", "1996-06-07 00:00:00", "1", "11111111111", "1", "2022-12-25 00:00:00", true));
		
	 	/*User user = userMapperReal.selectByEmail("test@naver.com");
	 	
	 	assertEquals(1, user.getUserId());
		assertEquals("1234", user.getPassword());
		assertEquals(true, user.getGender());
		*/
	}
	
	
	@Test
	public void realTest2()
	{
	 	User user = userMapperReal.selectByEmail("tncjftncjf@naver.com");
	 	
	 	assertEquals("���ö", user.getName());
	}
	
	@Test
	public void realTest3()
	{
		String name = userMapperReal.selectName("tncjftncjf@naver.com");
		
		assertEquals("���ö", name);
	}
	
}
