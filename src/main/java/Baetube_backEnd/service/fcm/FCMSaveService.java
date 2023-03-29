package Baetube_backEnd.service.fcm;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.mapper.UserMapper;
import io.jsonwebtoken.Claims;

public class FCMSaveService
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;
	
	public void saveFCMToken(String fcmToken, String accessToken) throws NullUserException
	{
		// accessToken���� ������ �����Ѵ�.
		Claims claims = jwtTokenProvider.parseToken(accessToken);
				
		// ���� �̸����� �����´�.
		String email = claims.getSubject();
		
		User user = userMapper.selectByEmail(email);
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		userMapper.updateFCMToken(fcmToken, email);
	}
}
