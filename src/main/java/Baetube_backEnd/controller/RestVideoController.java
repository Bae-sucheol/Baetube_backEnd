package Baetube_backEnd.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.checkerframework.checker.units.qual.s;
import org.modelmapper.internal.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.dto.VideoViewRequest;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.service.fcm.FCMSendService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.notification.NotificationInsertService;
import Baetube_backEnd.service.subscribe.SubscribeSelectService;
import Baetube_backEnd.service.video.ChannelVideoRequestService;
import Baetube_backEnd.service.video.HistoryVideoRequestService;
import Baetube_backEnd.service.video.MainVideoRequestService;
import Baetube_backEnd.service.video.PlaylistVideoRequestService;
import Baetube_backEnd.service.video.RelatedVideoRequestService;
import Baetube_backEnd.service.video.SubscribeVideoRequestService;
import Baetube_backEnd.service.video.VideoInsertService;
import Baetube_backEnd.service.video.VideoUpdateService;
import Baetube_backEnd.service.video.VideoViewService;

@RestController
public class RestVideoController
{
	@Autowired
	private ChannelVideoRequestService channelVideoRequestService;
	@Autowired
	private HistoryVideoRequestService historyVideoRequestService;
	@Autowired
	private MainVideoRequestService mainVideoRequestService;
	@Autowired
	private PlaylistVideoRequestService playlistVideoRequestService;
	@Autowired
	private SubscribeVideoRequestService subscribeVideoRequestService;
	@Autowired
	private VideoInsertService videoInsertService;
	@Autowired
	private VideoUpdateService videoUpdateService;
	@Autowired
	private VideoViewService videoViewService;
	@Autowired
	private RelatedVideoRequestService relatedVideoRequestService;
	@Autowired
	private SubscribeSelectService subscribeSelectService;
	@Autowired
	private FCMSendService fcmSendService;
	@Autowired
	private NotificationInsertService notificationInsertService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	
	@GetMapping("/api/video/channel_video/{channelId}")
	public ResponseEntity<Object> getChannelVideo(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{                                
		try
		{
			List<Video> videoList = channelVideoRequestService.requestVideo(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/video/history_video/{userId}")
	public ResponseEntity<Object> getHistoryVideo(@PathVariable Integer userId, HttpServletResponse response) throws IOException
	{                                           
		try
		{
			List<Video> videoList = historyVideoRequestService.requestVideo(userId);
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/video/main_video")
	public ResponseEntity<Object> getMainVideo(@RequestHeader("Authorization") String bearerToken, HttpServletResponse response) throws IOException
	{                                         
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			List<Video> videoList = mainVideoRequestService.requestVideo(user.getUserId());
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/video/playlist_video/{playlistId}")
	public ResponseEntity<Object> getPlaylistVideo(@PathVariable Integer playlistId, HttpServletResponse response) throws IOException
	{                                   
		try
		{
			List<Video> videoList = playlistVideoRequestService.requestVideo(playlistId);
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullPlaylistException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catch (NullVideoException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/video/subscribe_video/{channelSequence}")
	public ResponseEntity<Object> getSubscribeVideo(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{                                         
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			List<Video> videoList = subscribeVideoRequestService.requestVideo(channel.getChannelId());
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/video/insert")
	public ResponseEntity<Object> insertVideo(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid Video request, Errors errors, HttpServletResponse response) throws IOException, FirebaseMessagingException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			// channelId를 사용하여 channelSequence를 대체한다.
			// 이 메소드가 실행되고 난 이후 channel 객체의 channelId 속성을 request 객체에 다시 적용한다.
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, request.getChannelId());
			request.setChannelId(channel.getChannelId());
			
			HashMap<String, String> result = videoInsertService.insert(request);
			
			// 삽입 후 notification 테이블에 삽입하기 위해 구독자 userId를 select 한다.
			List<Integer> subscribersUserId = subscribeSelectService.selectChannelSubscribersUserId(request.getChannelId());
			// notification 테이블에 삽입.
			notificationInsertService.insert(subscribersUserId, Long.parseLong(result.get("contentsId")));
			
			List<String> subscribersTokens = subscribeSelectService.selectChannelSubscribersToken(request.getChannelId());
			
			// 알림을 보낼 대상이 존재한다면 알림을 보내야한다.
			if(!subscribersTokens.isEmpty())
			{
				fcmSendService.sendMultiMessage(subscribersTokens, request.getName() + "에서 새로운 동영상을 업로드 했습니다.",
						FCMSendService.FCM_NOTIFICATION_VIDEO, result.get(FCMSendService.FCM_NOTIFICATION_VIDEO));
			}

			result.remove(FCMSendService.FCM_NOTIFICATION_VIDEO);
			result.remove("contentsId");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} 
		catch (Exception e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/video/update")
	public ResponseEntity<Object> updateVideo(@RequestBody @Valid Video request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			videoUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("동영상이 존재하지 않습니다.");
		}
		
	}
	
	@GetMapping("/api/video/view_video/{videoId}")
	public ResponseEntity<Object> viewVideo(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer videoId, HttpServletResponse response) throws IOException
	{                                            
		try
		{
			User user = jwtTokenDataExtractService.getUserData(bearerToken);
			Video video = videoViewService.selectVideo(user.getUserId(), videoId);
			return ResponseEntity.status(HttpStatus.OK).body(video);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@GetMapping("/api/video/related/{videoId}")
	public ResponseEntity<Object> getRelatedVideo(@PathVariable Integer videoId, HttpServletResponse response) throws IOException
	{                                         
		try
		{
			List<Video> videoList = relatedVideoRequestService.requestVideo(videoId);
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}
