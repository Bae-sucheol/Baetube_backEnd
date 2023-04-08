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
	 * ������� ������ ��ū�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�.
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
	 * ������ ��ū���� �̸����� �����ϴ� �޼ҵ�
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
		// ������ ��ū ����
		String accessToken = getAccessToken(response);

		// ������ ��ū�� ����Ͽ� ���� �̸����� �����´�.
		String email = getUserEmail(accessToken);
		
		// ���� ���۸� ���� ������ ������ ��ȸ�Ѵ�.
		User user = userMapper.selectByEmail(email);
		
		if(user == null)
		{
			throw new NullUserException();
		}
		
		return user;
	}
	
	/**
	 * jwt��ū�� ���� ä���� ������ �����Ѵ�.
	 * �������� 0������ �����Ѵ�.
	 * @param response
	 * @param channelSequence
	 * @return
	 * @throws NullChannelException
	 */
	@Transactional
	public Channel getChannelData(HttpServletResponse response, Integer channelSequence) throws NullChannelException
	{
		// ������ ��ū ����
		String accessToken = getAccessToken(response);

		// ������ ��ū�� ����Ͽ� ���� �̸����� �����´�.
		String email = getUserEmail(accessToken);
		
		List<Channel> channelList = channelMapper.selectChannelByUserEmail(email);
		
		if(channelList == null || channelList.isEmpty())
		{
			throw new NullChannelException(); 
		}
		
		return channelList.get(channelSequence);
	}
}
