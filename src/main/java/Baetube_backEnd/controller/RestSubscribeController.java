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
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.exception.DuplicateSubscriberException;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.exception.WrongIdPasswordException;
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
	
	@PostMapping("/api/subscribe/subscribe")
	public ResponseEntity<Object> subscribe(@RequestBody @Valid Subscribers request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{	
			System.out.println("요청이 도착했습니다.");
			System.out.println("channelId : " + request.getChannelId());
			System.out.println("subscriberId : " + request.getSubscriberId());
			subscribeInsertService.insert(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicateSubscriberException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/subscribe/unsubscribe")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> unsubscribe(@RequestBody @Valid List<Subscribers> request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			System.out.println("요청이 도착했습니다.");
			subscribeDeleteService.delete(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullSubscriberException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/subscribe/select/{channelId}/{subscriberId}")
	public ResponseEntity<Object> selectSubscriber(@PathVariable Integer channelId, @PathVariable Integer subscriberId, HttpServletResponse response) throws IOException
	{                               
		try
		{
			Subscribers subscribers = subscribeSelectService.select(channelId, subscriberId);
			return ResponseEntity.status(HttpStatus.OK).body(subscribers);
		} 
		catch (NullSubscriberException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
