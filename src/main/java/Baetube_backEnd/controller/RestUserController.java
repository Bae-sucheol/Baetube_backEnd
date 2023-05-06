package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.dto.ChangePasswordRequest;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.TokenInfo;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.ExpiredRefreshTokenException;
import Baetube_backEnd.exception.NullUserException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.jwt.JwtAccessTokenService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
import Baetube_backEnd.service.user.ChangePasswordService;
import Baetube_backEnd.service.user.UserLoginService;
import Baetube_backEnd.service.user.UserRegisterService;
import Baetube_backEnd.service.user.UserSelectService;
import Baetube_backEnd.service.user.UserUnregisterService;
import Baetube_backEnd.service.user.UserUpdateService;
import Baetube_backEnd.ErrorResponse;

@RestController
public class RestUserController
{
	
	@Autowired
	private UserRegisterService userRegisterService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private UserUnregisterService userUnregisterService;
	@Autowired
	private ChangePasswordService changePasswordService;
	@Autowired
	private UserUpdateService userUpdateService;
	@Autowired
	private JwtAccessTokenService JwtAccessTokenService;
	@Autowired
	private PlaylistInsertService playlistInsertService;
	@Autowired
	private ChannelInsertService channelInsertService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	@Autowired
	private UserSelectService userSelectService;
	

	@PostMapping("/api/user/regist")
	public ResponseEntity<Object> newUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			User newUser = userRegisterService.regist(request);
			
			// ȸ�� ���� �� �⺻������ ä���� �ϳ� �����ؾ��Ѵ�.
			Channel newChannel = new Channel(newUser.getUserId(), newUser.getName());
			channelInsertService.insertChannel(newChannel);
			
			// ä�� ���� �� �ش� ä���� id�� �޾ƿ� ���ƿ並 ���� ������, ���߿� �� ������ �������� �߰��ؾ� �Ѵ�.
			// visible(public)�� 3���� �־� 3�̶�� visible(public)�� ������ �� ������ �Ѵ�.
			Playlist playlistLikeVideo = new Playlist(newChannel.getChannelId(), "���ƿ並 ���� ������", 3, "");
			Playlist playlistAfterWatching = new Playlist(newChannel.getChannelId(), "���߿� �� ������", 3, "");
			
			playlistInsertService.insert(playlistLikeVideo);
			playlistInsertService.insert(playlistAfterWatching);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
			
	@PostMapping("/api/user/login")
	public ResponseEntity<Object> loginUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			TokenInfo tokenInfo = userLoginService.login(request);
			return ResponseEntity.status(HttpStatus.OK).body(tokenInfo);
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (WrongIdPasswordException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (BadCredentialsException e)
		{
			System.out.println("BadCredentialsException");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
	@PostMapping("/api/user/unregist")
	public ResponseEntity<Object> unRegistUser(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			userUnregisterService.unRegist(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/change_password")
	public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			changePasswordService.changePassword(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (WrongIdPasswordException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/user/update")
	public ResponseEntity<Object> userUpdate(@RequestBody User request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			userUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/generate/access")
	public ResponseEntity<Object> generateAccessToken(@RequestBody TokenInfo request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			TokenInfo tokenInfo = JwtAccessTokenService.generateToken(request);
			return ResponseEntity.status(HttpStatus.OK).body(tokenInfo);
		} 
		catch (ExpiredRefreshTokenException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).header("Exception", "ExpiredRefreshTokenException").build();
		}

	}
	
	@GetMapping("/api/user/data")
	public ResponseEntity<Object> getUserDataFromToken(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{
                                           
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			
			user = userSelectService.selectUserByEmail(user.getEmail());
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} 
		catch (NullUserException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
