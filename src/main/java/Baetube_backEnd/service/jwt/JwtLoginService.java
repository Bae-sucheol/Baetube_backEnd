package Baetube_backEnd.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;

public class JwtLoginService
{
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Transactional
	public TokenInfo login(String email, String password)
	{
		// Authentication ��ü ����
		// ���� ����(authenticated ��)�� false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		
		// ���� ����
		// authenticate �ż��尡 ����� �� CustomUserDetailsService ���� ���� loadUserByUsername �޼��尡 ����
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// ���� ������ ������� ��ū ����
		TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		
		return tokenInfo;
	}
}
