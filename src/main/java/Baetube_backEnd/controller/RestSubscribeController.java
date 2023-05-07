package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.DuplicateSubscriberException;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.subscribe.SubscribeDeleteService;
import Baetube_backEnd.service.subscribe.SubscribeInsertService;
import Baetube_backEnd.service.subscribe.SubscribeSelectService;

@RestController
public class RestSubscribeController
{
	@Autowired
	private SubscribeInsertService subscribeInsertService;
	@Autowired
	private SubscribeDeleteService subscribeDeleteService;
	@Autowired
	private SubscribeSelectService subscribeSelectService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	@PostMapping("/api/subscribe/subscribe/{channelSequence}")
	public ResponseEntity<Object> subscribe(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Integer channelId, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{	
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			Subscribers subscribers = new Subscribers(channelId, channel.getChannelId());
			subscribeInsertService.insert(subscribers);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateSubscriberException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/subscribe/unsubscribe/{channelSequence}")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> unsubscribe(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid List<Subscribers> request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			subscribeDeleteService.delete(request, channel.getChannelId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullSubscriberException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/subscribe/select/{channelId}/{channelSequence}")
	public ResponseEntity<Object> selectSubscriber(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelId, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{                               
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			Subscribers subscribers = subscribeSelectService.select(channelId, channel.getChannelId());
			return ResponseEntity.status(HttpStatus.OK).body(subscribers);
		} 
		catch (NullSubscriberException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
