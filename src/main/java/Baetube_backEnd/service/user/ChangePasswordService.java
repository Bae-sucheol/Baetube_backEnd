package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.ChangePasswordRequest;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class ChangePasswordService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public boolean changePassword(ChangePasswordRequest request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
		
		userMapper.changePassword(request.getEmail(), passwordEncoder.encode(request.getNewPassword()));
		
		return true;
	}
}
