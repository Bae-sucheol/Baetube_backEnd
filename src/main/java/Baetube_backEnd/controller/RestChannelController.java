package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.service.channel.ChannelDeleteService;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.channel.ChannelSelectService;
import Baetube_backEnd.service.channel.ChannelSubscribersService;
import Baetube_backEnd.service.channel.ChannelUpdateService;
import Baetube_backEnd.service.channel.ChannelVisitService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
 
@RestController
public class RestChannelController
{
	@Autowired
	private ChannelDeleteService channelDeleteService;
	@Autowired
	private ChannelInsertService channelInsertService;
	@Autowired
	private ChannelUpdateService channelUpdateService;
	@Autowired
	private ChannelVisitService channelVisitService;
	@Autowired
	private ChannelSubscribersService channelSubscribersService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	@Autowired
	private ChannelSelectService channelSelectService;
	
	@PostMapping("/api/channel/delete")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deleteChannel(@RequestBody @Valid Channel request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			channelDeleteService.deleteChannel(request.getChannelId());
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	
	}
	
	@PostMapping("/api/channel/insert")
	public ResponseEntity<Object> insertChannel(@RequestBody @Valid Channel request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			channelInsertService.insertChannel(request);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/channel/update")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> updateChannel(@RequestBody @Valid Channel request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			HashMap<String, String> isChangedImages = channelUpdateService.updateChannel(request);
			
			return ResponseEntity.status(HttpStatus.OK).body(isChangedImages);
		} 
		catch (NullChannelException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 채널입니다.");
		}
		
	}
	
	
	
	@GetMapping("/api/channel/visit/{channelId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectChannel(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{
		                            
		try
		{
			Channel channel = channelVisitService.selectChannel(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(channel);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/channel/subscribers/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectSubscribers(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{
                                           
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			List<Channel> subscriberList = channelSubscribersService.selectSubscribers(channel.getChannelId());
			return ResponseEntity.status(HttpStatus.OK).body(subscriberList);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/channel/data/{channelSequence}")
	public ResponseEntity<Object> selectChannelData(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{
                                          
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			return ResponseEntity.status(HttpStatus.OK).body(channel);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/channel/search/{keywords}")
	public ResponseEntity<Object> selectChannelData(@PathVariable String keywords, HttpServletResponse response) throws IOException
	{
                                          
		try
		{
			List<Channel> channelList = channelSelectService.selectByKeywords(keywords);
			return ResponseEntity.status(HttpStatus.OK).body(channelList);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
