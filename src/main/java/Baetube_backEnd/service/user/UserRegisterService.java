package Baetube_backEnd.service.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.mapper.UserMapper;

@Service
public class UserRegisterService
{
	
	@Autowired
	private UserMapper userMapper;
	
	public void setUserMapper(UserMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	@Transactional
	public Integer regist(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if (user != null)
		{
			throw new DuplicateUserException("이미 존재하는 계정입니다. " + request.getEmail());
		}

		// 이 부분에 fcm 토큰을 생성해야 한다.
		String fcmToken = "token";
		
		User newUser = new User(request.getUserId(), request.getEmail(), request.getPassword(), request.getName(),
				request.getGender(), request.getBirth(), fcmToken, request.getPhone(), request.getAddress(), request.getRegDate());
		
		userMapper.insert(newUser);
		
		return newUser.getUserId();
	}
	
}
