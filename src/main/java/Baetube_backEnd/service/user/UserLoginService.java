package Baetube_backEnd.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.UserMapper;


public class UserLoginService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;

	@Transactional
	public TokenInfo login(User user) throws BadCredentialsException
	{
		// Authentication ��ü ����
		// ���� ����(authenticated ��)�� false
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		// ���� ����
		// authenticate �ż��尡 ����� �� CustomUserDetailsService ���� ���� loadUserByUsername �޼��尡 ����
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// ���� ������ ������� ��ū ����
		TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
		
		// ������ refreshToken�� db�� �����Ѵ�.
		userMapper.updateRefreshToken(user.getEmail(), tokenInfo.getRefreshToken());
		
		return tokenInfo;
	}
	
	
	/*
	@Transactional
	public User login(User request)
	{
		User user = userMapper.selectByEmail(request.getEmail());
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		if(!user.getPassword().equals(request.getPassword()))
		{
			throw new WrongIdPasswordException();
		}
		
		return user;
	}
	*/
}
