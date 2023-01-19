package Baetube_backEnd.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.UserMapper;

public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{	
		User user = userMapper.selectByEmail(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("일치하는 유저가 존재하지 않습니다.");
		}
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword()))
				//.roles(user.getRoles())
				.roles("USER")
				.build();
				
	}

}
