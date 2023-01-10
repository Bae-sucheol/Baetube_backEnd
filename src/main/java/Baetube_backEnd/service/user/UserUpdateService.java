package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.mapper.UserMapper;

public class UserUpdateService
{
	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public boolean update(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if (user == null)
		{
			throw new WrongIdPasswordException();
		}
		
		// 기타 입력에 대한 검증을 해야한다.
		
		userMapper.update(user, request);;
		
		return true;
	}
}
