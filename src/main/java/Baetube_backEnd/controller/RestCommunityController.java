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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.service.community.CommunityChannelVisitService;
import Baetube_backEnd.service.community.CommunityDeleteService;
import Baetube_backEnd.service.community.CommunityInsertService;
import Baetube_backEnd.service.fcm.FCMSendService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.notification.NotificationInsertService;
import Baetube_backEnd.service.subscribe.SubscribeSelectService;

@RestController
public class RestCommunityController
{
	@Autowired
	private CommunityChannelVisitService communityChannelVisitService;
	@Autowired
	private CommunityDeleteService communityDeleteService;
	@Autowired
	private CommunityInsertService communityInsertService;
	@Autowired
	private SubscribeSelectService subscribeSelectService;
	@Autowired
	private FCMSendService fcmSendService;
	@Autowired
	private NotificationInsertService notificationInsertService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;

	@GetMapping("/api/community/channel_visit/{channelId}/{channelSequence}")
	public ResponseEntity<Object> getChannelCommunity(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelId, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{
		try
		{
			Channel requestChannel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			List<Community> communityList = communityChannelVisitService.selectCommunity(channelId, requestChannel.getChannelId());
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
	
	@PostMapping("/api/community/insert/{channelSequence}")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertCommunity(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Community request, Errors errors, HttpServletResponse response) throws IOException, FirebaseMessagingException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			request.setChannelId(channel.getChannelId());
			// 삽입 후 결과를 받아온다.
			HashMap<String, String> result = communityInsertService.insertCommunity(request);
			// 삽입 후 notification 테이블에 삽입하기 위해 구독자 userId를 select 한다.
			List<Integer> subscribersUserId = subscribeSelectService.selectChannelSubscribersUserId(request.getChannelId());
			// notification 테이블에 삽입.
			notificationInsertService.insert(subscribersUserId, Long.parseLong(result.get("contentsId")));
			
			List<String> subscribersTokens = subscribeSelectService.selectChannelSubscribersToken(request.getChannelId());
			
			// 알림을 보낼 대상이 존재한다면 알림을 보내야한다.
			if(!subscribersTokens.isEmpty())
			{
				fcmSendService.sendMultiMessage(subscribersTokens, request.getName() + "에서 새로운 게시글을 업로드 했습니다.",
						FCMSendService.FCM_NOTIFICATION_COMMUNITY, result.get(FCMSendService.FCM_NOTIFICATION_COMMUNITY));
			}
			
			result.remove(FCMSendService.FCM_NOTIFICATION_COMMUNITY);
			result.remove("contentsId");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		catch (DuplicateUserException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
