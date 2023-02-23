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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.service.community.CommunityChannelVisitService;
import Baetube_backEnd.service.community.CommunityDeleteService;
import Baetube_backEnd.service.community.CommunityInsertService;

@RestController
public class RestCommunityController
{
	@Autowired
	private CommunityChannelVisitService communityChannelVisitService;
	@Autowired
	private CommunityDeleteService communityDeleteService;
	@Autowired
	private CommunityInsertService communityInsertService;

	@GetMapping("/api/community/channel_visit/{channelId}")
	public ResponseEntity<Object> getChannelCommunity(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{
		try
		{
			List<Community> communityList = communityChannelVisitService.selectCommunity(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(communityList);
		} 
		catch (NullCommunityException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/community/delete")	
	public ResponseEntity<Object> deleteCommunity(@RequestBody @Valid Community request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			communityDeleteService.deleteCommunity(request.getCommunityId());
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullCommunityException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/community/insert")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> getHistoryVideo(@RequestBody @Valid Community request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			HashMap<String, String> result = communityInsertService.insertCommunity(request);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		catch (DuplicateUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
