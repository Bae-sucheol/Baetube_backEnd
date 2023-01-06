package Baetube_backEnd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.UserMapper;

public class ChannelInsertServiceTest
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
	public void realSelectByEmailTest()
	{
	 	User user = userMapperReal.selectByEmail("tncjftncjf@naver.com");
	 	
	 	System.out.println(" ¿©±â¾ß " + user.getName());
	 	
	 	assertEquals("tncjftncjf@naver.com", user.getName());
	}
	
	
}
