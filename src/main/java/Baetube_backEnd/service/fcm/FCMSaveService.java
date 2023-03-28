package Baetube_backEnd.service.fcm;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.JwtTokenProvider;
import io.jsonwebtoken.Claims;

public class FCMSaveService
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public void saveFCMToken(String fcmToken, String accessToken)
	{
		Claims claims = jwtTokenProvider.parseToken(accessToken);
		claims.get
	}
}
