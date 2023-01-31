package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.checkerframework.checker.units.qual.cd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.service.channel.ChannelDeleteService;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.channel.ChannelSubscribersService;
import Baetube_backEnd.service.channel.ChannelUpdateService;
import Baetube_backEnd.service.channel.ChannelVisitService;

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
	//@ExceptionHandler(MethodArgumentNotValidException.class)
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
			channelUpdateService.updateChannel(request);
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (Exception e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	
	
	@GetMapping("/api/channel/visit/{channelId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectChannel(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{
		                            
		try
		{
			System.out.println("요청이 들어왔습니다.");
			Channel channel = channelVisitService.selectChannel(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(channel);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/channel/subscribers/{channelId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectSubscribers(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{
                                           
		try
		{
			List<Channel> subscriberList = channelSubscribersService.selectSubscribers(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(subscriberList);
		} 
		catch (NullChannelException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
