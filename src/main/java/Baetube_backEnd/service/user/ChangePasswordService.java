package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.ChangePasswordRequest;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class ChangePasswordService
{
	@Autowired
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	public boolean changePassword(ChangePasswordRequest request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if(user == null)
		{
			throw new WrongIdPasswordException();
		}
		
		if(!user.getPassword().equals(request.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
		
		userMapper.changePassword(request.getEmail(), request.getNewPassword());
		
		return true;
	}
}