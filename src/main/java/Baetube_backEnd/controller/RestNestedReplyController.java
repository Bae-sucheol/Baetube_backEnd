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

import com.google.firebase.messaging.FirebaseMessagingException;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.exception.NullReplyException;
import Baetube_backEnd.service.fcm.FCMSendService;
import Baetube_backEnd.service.nestedreply.NestedReplyInsertService;
import Baetube_backEnd.service.nestedreply.NestedReplySelectService;
import Baetube_backEnd.service.nestedreply.NestedReplyUpdateService;


@RestController
public class RestNestedReplyController
{	
	@Autowired
	private NestedReplyInsertService nestedReplyInsertService;
	@Autowired
	private NestedReplySelectService nestedReplySelectService;
	@Autowired
	private NestedReplyUpdateService nestedReplyUpdateService;
	@Autowired
	private FCMSendService fcmSendService;
	
	@PostMapping("/api/nestedreply/insert")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertNestedReply(@RequestBody @Valid NestedReply request, Errors errors, HttpServletResponse response) throws IOException, FirebaseMessagingException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			HashMap<String, String> result = nestedReplyInsertService.insertNestedReply(request);
			
			// 타입이 0이면(동영상이면) 
			if(Integer.parseInt(result.get("type")) == 0)
			{
				fcmSendService.sendMessage(result.get(FCMSendService.FCM_NOTIFICATION_REPLY), request.getName() + "님이 대댓글을 작성했습니다.",
					FCMSendService.FCM_NOTIFICATION_NESTED_REPLY, result.get(FCMSendService.FCM_NOTIFICATION_NESTED_REPLY),
					FCMSendService.FCM_NOTIFICATION_VIDEO, result.get(FCMSendService.FCM_NOTIFICATION_VIDEO));
			}
			else // 타입이 0이 아니면(게시판이면)
			{
				fcmSendService.sendMessage(result.get(FCMSendService.FCM_NOTIFICATION_REPLY), request.getName() + "님이 대댓글을 작성했습니다.",
						FCMSendService.FCM_NOTIFICATION_NESTED_REPLY, result.get(FCMSendService.FCM_NOTIFICATION_NESTED_REPLY),
						FCMSendService.FCM_NOTIFICATION_COMMUNITY, result.get(FCMSendService.FCM_NOTIFICATION_COMMUNITY));
			}
			
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullReplyException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/nestedreply/select/{replyId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectNestedReply(@PathVariable Integer replyId, HttpServletResponse response) throws IOException
	{                                            
		try
		{
			List<NestedReply> nestedReplyList = nestedReplySelectService.selectNestedReply(replyId);
			return ResponseEntity.status(HttpStatus.OK).body(nestedReplyList);
		} 
		catch (NullReplyException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/nestedreply/update")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> updateNestedReply(@RequestBody @Valid NestedReply request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			nestedReplyUpdateService.updateNestedReply(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullReplyException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
