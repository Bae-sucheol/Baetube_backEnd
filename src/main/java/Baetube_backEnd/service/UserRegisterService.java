package Baetube_backEnd.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.mapper.UserMapper;

@Service
public class UserRegisterService
{
	
	private UserMapper userMapper;
	
	public UserRegisterService()
	{
		
	}
	
	public UserRegisterService(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	public Integer regist(User register)
	{
		User user = userMapper.selectByEmail(register.getEmail());
		
		if (user != null)
		{
			throw new DuplicateUserException("�̹� �����ϴ� �����Դϴ�. " + register.getEmail());
		}

		// �� �κп� fcm ��ū�� �����ؾ� �Ѵ�.
		String fcmToken = "token";
		
		User newUser = new User(register.getUserId(), register.getEmail(), register.getPassword(), register.getName(),
				register.getGender(), register.getBirth(), fcmToken, register.getPhone(), register.getAddress(), register.getRegDate());
		
		userMapper.insert(newUser);
		
		return newUser.getUserId();
	}
	
}
