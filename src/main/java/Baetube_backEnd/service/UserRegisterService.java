package Baetube_backEnd.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateMemberException;
import Baetube_backEnd.mapper.UserMapper;

@Service
public class UserRegisterService
{
	
	@Autowired
	private UserMapper userMapper;
	
	public Integer regist(User register)
	{
		User user = userMapper.selectByEmail(register.getEmail());
		
		if (user != null)
		{
			throw new DuplicateMemberException("이미 존재하는 계정입니다. " + register.getEmail());
		}

		// 이 부분에 fcm 토큰을 생성해야 한다.
		String fcmToken = "token";
		
		User newUser = new User(register.getEmail(), register.getPassword(), register.getName(),
				register.getGender(), register.getBirth(), fcmToken, register.getPhone(), register.getAddress());
		
		userMapper.insert(newUser);
		
		return newUser.getUserId();
	}
	
}
