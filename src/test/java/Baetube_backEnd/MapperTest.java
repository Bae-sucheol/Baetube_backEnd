package Baetube_backEnd;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.UserMapper;

/*
 * Controller.java - Service.java - Mapper.java - Mapper.xml 구조를 사용하는 것이 
 * Controller.java - Service.java - DAO.java - Mapper.xml 구조를 사용하는 방식 보다 최근에 사용되고 있는 방식이라고 한다.
 * 전자의 구조를 사용하면 후자보다 간편하게 사용할 수 있다. 
 * 1. DB에서 Email을 사용하여 해당 Email을 사용하는 유저의 정보를 조회하는 기능(비밀번호 찾기 등에 사용.)
 * 2. DB에 작성한 유저의 정보를 삽입하는 메소드
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
		User user = new User("test@naver.com", "1234", "test", 1, new Timestamp(System.nanoTime()), "1", "11111111111", "11111111", new Timestamp(System.nanoTime()));
		
		when(userMapper.selectByEmail("test@naver.com")).thenReturn(user);
		
		User user2 = userMapper.selectByEmail("test@naver.com");
		
		assertEquals("1234", user2.getPassword());
		assertTrue(user2.getGender() == 1);
	}
	

	@Test
	public void mockInsertTest()
	{
		User insert = new User("test2@naver.com", "1234", "test2", 1, new Timestamp(System.nanoTime()), "1", "11111111111", "11111111", new Timestamp(System.nanoTime()));
		
		userMapper.insert(insert);
		verify(userMapper).insert(insert);
	}
	

	@Test
	public void realInsertTest()
	{
		User insert = new User("test@naver.com", "1234", "test", 1, new Timestamp(System.nanoTime()), "1", "11111111111", "11111111", new Timestamp(System.nanoTime()));
		
		userMapperReal.insert(insert);
		
	 	User user = userMapperReal.selectByEmail("test@naver.com");
	 	
	 	assertEquals("test@naver.com", user.getEmail());
		assertEquals("1234", user.getPassword());
		assertEquals("test", user.getName());
		assertTrue(user.getGender() == 1);
	}
	

	@Test
	public void realSelectByEmailTest()
	{
	 	User user = userMapperReal.selectByEmail("tncjftncjf@naver.com");
	 	
	 	assertEquals("배수철", user.getName());
	}
	
	@Test
	public void realUpdateTest()
	{
		User oldUser = userMapperReal.selectByEmail("test@naver.com");
		User newUser = new User("test@naver.com", "1234", "test", 1, new Timestamp(System.nanoTime()), "1", "11111111111", "11111111", new Timestamp(System.nanoTime()));
		newUser.setName("배수철");
		newUser.setAddress("경기도 남양주시");
		newUser.setGender(0);
		
		userMapperReal.update(oldUser, newUser);
		
		User user = userMapperReal.selectByEmail("test@naver.com");
	 	
		assertEquals("배수철", user.getName());
		assertEquals("경기도 남양주시", user.getAddress());
		
	}
	
}
