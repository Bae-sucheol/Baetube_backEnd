package Baetube_backEnd.service.jwt;

import static org.mockito.Matchers.contains;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.UserMapper;
import io.jsonwebtoken.Claims;

public class JwtTokenDataExtractService
{
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ChannelMapper channelMapper;
	
	/**
	 * 헤더에서 엑세스 토큰을 추출하여 반환하는 메소드.
	 * @param response
	 * @return
	 */
	public String getAccessToken(HttpServletResponse response)
	{
		String accessToken = response.getHeader("Authorization");
		
		accessToken = accessToken.split(" ")[1].trim();
		
		return accessToken;
	}
	
	/**
	 * 엑세스 토큰으로 이메일을 추출하는 메소드
	 * @param accessToken
	 * @return
	 */
	public String getUserEmail(String accessToken)
	{
		Claims claims = jwtTokenProvider.parseToken(accessToken);
		
		String email = claims.getSubject();
		
		return email;
	}
	
	@Transactional
	public User getUserData(HttpServletResponse response) throws NullUserException
	{
		// 엑세스 토큰 추출
		String accessToken = getAccessToken(response);

		// 엑세스 토큰을 사용하여 유저 이메일을 가져온다.
		String email = getUserEmail(accessToken);
		
		// 유저 매퍼를 통해 유저의 정보를 조회한다.
		User user = userMapper.selectByEmail(email);
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		return user;
	}
	
	/**
	 * jwt토큰을 통해 채널의 정보를 추출한다.
	 * 시퀀스는 0번부터 시작한다.
	 * @param response
	 * @param channelSequence
	 * @return
	 * @throws NullChannelException
	 */
	@Transactional
	public Channel getChannelData(HttpServletResponse response, Integer channelSequence) throws NullChannelException
	{
		// 엑세스 토큰 추출
		String accessToken = getAccessToken(response);

		// 엑세스 토큰을 사용하여 유저 이메일을 가져온다.
		String email = getUserEmail(accessToken);
		
		List<Channel> channelList = channelMapper.selectChannelByUserEmail(email);
		
		if(channelList == null || channelList.isEmpty())
		{
			throw new NullChannelException(); 
		}
		
		return channelList.get(channelSequence);
	}
}
