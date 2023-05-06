package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.mapper.UserMapper;

public class UserSelectService
{
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public User selectUserByEmail(String request) throws NullUserException
	{
		User user = userMapper.selectByEmail(request);
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		user.setPassword(new String());
		
		return user;
	}
}
