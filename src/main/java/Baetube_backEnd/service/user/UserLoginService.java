package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;


public class UserLoginService
{
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public User login(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		if(!user.getPassword().equals(request.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
		
		return user;
	}
}
