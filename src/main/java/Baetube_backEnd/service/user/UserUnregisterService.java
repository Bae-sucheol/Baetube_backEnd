package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class UserUnregisterService
{

	@Autowired
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	public boolean unRegist(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if (user == null)
		{
			throw new WrongIdPasswordException();
		}
		
		if (!user.getPassword().equals(request.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
			
		userMapper.delete(user.getUserId());
		
		return true;
	}
}
