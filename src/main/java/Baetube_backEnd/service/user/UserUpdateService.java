package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class UserUpdateService
{
	@Autowired
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	public boolean update(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if (user == null)
		{
			throw new WrongIdPasswordException();
		}
		
		// ��Ÿ �Է¿� ���� ������ �ؾ��Ѵ�.
		
		userMapper.update(user, request);;
		
		return true;
	}
}