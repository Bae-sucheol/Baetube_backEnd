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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.reply.ReplyInsertService;
import Baetube_backEnd.service.reply.ReplySelectService;
import Baetube_backEnd.service.reply.ReplyUpdateService;

@RestController
public class RestReplyController
{
	@Autowired
	private ReplyInsertService replyInsertService;
	@Autowired
	private ReplySelectService replySelectService;
	@Autowired
	private ReplyUpdateService replyUpdateService;
	
	@PostMapping("/api/reply/insert")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertReply(@RequestBody @Valid Reply request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			replyInsertService.insertReply(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (WrongIdPasswordException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/reply/select/{contentsId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectReply(@PathVariable Long contentsId, HttpServletResponse response) throws IOException
	{                                      
		try
		{
			List<Reply> replyList = replySelectService.selectReply(contentsId);
			return ResponseEntity.status(HttpStatus.OK).body(replyList);
		} 
		catch (NullReplyException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/reply/update")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> updateReply(@RequestBody @Valid Reply request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			
			replyUpdateService.updateReply(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullReplyException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
