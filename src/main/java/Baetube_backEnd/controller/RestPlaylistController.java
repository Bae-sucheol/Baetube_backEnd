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
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;
import Baetube_backEnd.service.playlist.PlaylistChannelService;
import Baetube_backEnd.service.playlist.PlaylistDeleteItemService;
import Baetube_backEnd.service.playlist.PlaylistDeleteLikeVideoService;
import Baetube_backEnd.service.playlist.PlaylistDeleteService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemMultiService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemService;
import Baetube_backEnd.service.playlist.PlaylistInsertLikeVideoService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
import Baetube_backEnd.service.playlist.PlaylistSelectService;
import Baetube_backEnd.service.playlist.PlaylistUpdateService;

@RestController
public class RestPlaylistController
{
	@Autowired
	private PlaylistChannelService playlistChannelService;
	@Autowired
	private PlaylistInsertService playlistInsertService;
	@Autowired
	private PlaylistDeleteService playlistDeleteService;
	@Autowired
	private PlaylistUpdateService playlistUpdateService;
	@Autowired
	private PlaylistInsertItemService playlistInsertItemService;
	@Autowired
	private PlaylistDeleteItemService playlistDeleteItemService;
	@Autowired
	private PlaylistInsertItemMultiService playlistInsertItemMultiService;
	@Autowired
	private PlaylistInsertLikeVideoService playlistInsertLikeVideoService;
	@Autowired
	private PlaylistDeleteLikeVideoService playlistDeleteLikeVideoService;
	@Autowired
	private JwtTokenDataExtractService jwtTokenDataExtractService;
	@Autowired
	private PlaylistSelectService playlistSelectService;
	
	@GetMapping("/api/playlist/channel/{channelId}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectChannelPlaylist(@PathVariable Integer channelId, HttpServletResponse response) throws IOException
	{                                             
		try
		{
			List<Playlist> playlistList = playlistChannelService.select(channelId);
			return ResponseEntity.status(HttpStatus.OK).body(playlistList);
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@GetMapping("/api/storage/channel/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectChannelStorage(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, HttpServletResponse response) throws IOException
	{                                             
		try
		{
			Channel channel = jwtTokenDataExtractService.getChannelData(bearerToken, channelSequence);
			List<Playlist> playlistList = playlistChannelService.select(channel.getChannelId());
			return ResponseEntity.status(HttpStatus.OK).body(playlistList);
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/insert/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertPlaylist(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Playlist request, Errors errors, HttpServletResponse response) throws IOException
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
			Integer playlistId = playlistInsertService.insert(request);
			return ResponseEntity.status(HttpStatus.OK).body(playlistId);
		} 
		catch (NullPlaylistException e)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/delete/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deletePlaylist(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Playlist request, Errors errors, HttpServletResponse response) throws IOException
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
			playlistDeleteService.delete(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/update")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> updatePlaylist(@RequestBody @Valid Playlist request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			HashMap<String, String> isChangedImage = playlistUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).body(isChangedImage);
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/item/insert")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertPlaylistItem(@RequestBody @Valid List<PlaylistItem> request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			playlistInsertItemService.insertItem(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (DuplicatePlaylistItemException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/item/delete")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deletePlaylistItem(@RequestBody @Valid PlaylistItem request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			playlistDeleteItemService.deleteItem(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistItemException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 아이템");
		}
	}
	
	@GetMapping("/api/playlist/item/{playlistId}")
	public ResponseEntity<Object> selectPlaylistItem(@PathVariable Integer playlistId, HttpServletResponse response) throws IOException
	{                                             
		try
		{
			System.out.println("요청이 도착했습니다.");
			List<Playlist> playlistList = playlistChannelService.select(playlistId);
			return ResponseEntity.status(HttpStatus.OK).body(playlistList);
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/item/insert/multi")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertPlaylistItemMulti(@RequestBody @Valid List<PlaylistItem> request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			playlistInsertItemMultiService.insertItemMulti(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistItemException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 아이템");
		}
	}
	
	@PostMapping("/api/playlist/item/insert/like/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertLikeVideo(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Video request, Errors errors, HttpServletResponse response) throws IOException
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
			playlistInsertLikeVideoService.insertLikeVideo(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistItemException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 아이템");
		}
	}
	
	@PostMapping("/api/playlist/item/delete/like/{channelSequence}")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deleteLikeVideo(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer channelSequence, @RequestBody @Valid Video request, Errors errors, HttpServletResponse response) throws IOException
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
			playlistDeleteLikeVideoService.deleteLikeVideo(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistItemException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 아이템");
		}
	}
	
	@GetMapping("/api/playlist/data/{playlistId}")
	public ResponseEntity<Object> selectPlaylistData(@PathVariable Integer playlistId, HttpServletResponse response) throws IOException
	{                                             
		try
		{
			Playlist playlist = playlistSelectService.selectPlaylistData(playlistId);
			return ResponseEntity.status(HttpStatus.OK).body(playlist);
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
