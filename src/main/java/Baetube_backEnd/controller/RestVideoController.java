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
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.dto.VideoViewRequest;
import Baetube_backEnd.exception.DuplicateUserException;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.service.video.ChannelVideoRequestService;
import Baetube_backEnd.service.video.HistoryVideoRequestService;
import Baetube_backEnd.service.video.MainVideoRequestService;
import Baetube_backEnd.service.video.PlaylistVideoRequestService;
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
	
	@GetMapping("/api/video/main_video/{userId}")
	public ResponseEntity<Object> getMainVideo(@PathVariable Integer userId, HttpServletResponse response) throws IOException
	{                                         
		try
		{
			List<Video> videoList = mainVideoRequestService.requestVideo(userId);
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
	
	@GetMapping("/api/video/subscribe_video/{channelId}")
	public ResponseEntity<Object> getSubscribeVideo(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{                                         
		try
		{
			List<Video> videoList = subscribeVideoRequestService.requestVideo(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(videoList);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
	@PostMapping("/api/video/insert")
	public ResponseEntity<Object> insertVideo(@RequestBody @Valid Video request, Errors errors, HttpServletResponse response) throws IOException
	{
		
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			HashMap<String, String> result = videoInsertService.insert(request);
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
			System.out.println("요청이 도착했습니다.");
			videoUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("동영상이 존재하지 않습니다.");
		}
		
	}
	
	@GetMapping("/api/video/view_video/{userId}/{videoId}")
	public ResponseEntity<Object> viewVideo(@PathVariable Integer userId, @PathVariable Integer videoId, HttpServletResponse response) throws IOException
	{                                            
		try
		{
			Video video = videoViewService.selectVideo(userId, videoId);
			return ResponseEntity.status(HttpStatus.OK).body(video);
		} 
		catch (NullVideoException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
}
