package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public User regist(User request) throws DuplicateUserException
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if (user != null)
		{
			throw new DuplicateUserException("이미 존재하는 계정입니다. " + request.getEmail());
		}
		
		User newUser = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getName(),
				request.getGender(), request.getBirth(), null, request.getPhone(), request.getAddress());
		
		userMapper.insert(newUser);
		
		return newUser;
	}
	
}
