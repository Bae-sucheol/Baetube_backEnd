package Baetube_backEnd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vam.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class TimeMapperTest
{
	@Autowired UserMapper userMapper;
	
	@Test
	public void testGetTime()
	{
		System.out.println(userMapper.getTime());
	}
	
	@Test
	public void testGetTime2()
	{
		System.out.println(userMapper.getTime2());
	}
	
	@Test
	public void testSelectName()
	{
		System.out.println(userMapper.selectName("tncjftncjf@naver.com"));
	}
}
