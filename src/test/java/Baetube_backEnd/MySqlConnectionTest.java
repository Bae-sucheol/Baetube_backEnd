package Baetube_backEnd;

import java.sql.Connection;


import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
@WebAppConfiguration
public class MySqlConnectionTest
{
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void connection() throws Exception 
	{
		try
		{
			Connection connection = dataSource.getConnection();
			
			System.out.println("Connection : " + connection);
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
