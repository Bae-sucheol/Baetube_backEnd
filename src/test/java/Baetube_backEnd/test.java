package Baetube_backEnd;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dto.Reply;

import mapper.ReplyMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class test
{
	@Autowired
	private ReplyMapper replyMapper;
	
	@Test
	public void replyTest()
	{
		for (int i = 0; i < 10; i++)
		{
			Reply reply = new Reply(1L, 1, "´ñ±Û Å×½ºÆ® " + (i + 1), new Timestamp(System.currentTimeMillis()));
			
			replyMapper.insert(reply);
		}
	}
}
