package Baetube_backEnd;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
@WebAppConfiguration
public class MyBatisTest
{
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Test
	public void factory()
	{ 
		System.out.println("Factory : " + sessionFactory);
	}
	
	@Test
	public void session() throws Exception
	{
		try
		{
			SqlSession session = sessionFactory.openSession();
			
			System.out.println("Session : " + session);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
