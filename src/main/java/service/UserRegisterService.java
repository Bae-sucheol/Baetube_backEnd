package service;

import org.springframework.beans.factory.annotation.Autowired;

import mapper.UserMapper;

public class UserRegisterService
{
	@Autowired
	private UserMapper userMapper;
	
	public int regist()
	{
		return 0;
	}
}
