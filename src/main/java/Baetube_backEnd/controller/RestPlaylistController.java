package Baetube_backEnd.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Baetube_backEnd.ErrorResponse;
import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.exception.WrongIdPasswordException;
import Baetube_backEnd.service.playlist.PlaylistChannelService;
import Baetube_backEnd.service.playlist.PlaylistDeleteItemService;
import Baetube_backEnd.service.playlist.PlaylistDeleteService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
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
	
	@GetMapping("/api/playlist/channel")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> selectChannelPlaylist(@RequestBody @Valid Channel request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			playlistChannelService.select(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/insert")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertPlaylist(@RequestBody @Valid Playlist request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
			playlistInsertService.insert(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/delete")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> deletePlaylist(@RequestBody @Valid Playlist request, Errors errors, HttpServletResponse response) throws IOException
	{
		if(errors.hasErrors())
		{
			String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = " + errorCodes));
		}
		                                                 
		try
		{
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
			playlistUpdateService.update(request);
			return ResponseEntity.status(HttpStatus.OK).build();
		} 
		catch (NullPlaylistException e)
		{
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/api/playlist/insert_item")
	//@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> insertPlaylistItem(@RequestBody @Valid PlaylistItem request, Errors errors, HttpServletResponse response) throws IOException
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
	
	@PostMapping("/api/playlist/delete_item")
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
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}