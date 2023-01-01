package Baetube_backEnd;

import java.sql.Timestamp;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.mapper.ChannelMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class ChannelMapperTest
{
	@Autowired
	ChannelMapper channelMapper;
	
	@Test
	public void insertVideoTest()
	{
		Channel channel = new Channel(2, "testChannel", new Timestamp(System.currentTimeMillis()));
			
		channelMapper.insert(channel);
	}
	
}
