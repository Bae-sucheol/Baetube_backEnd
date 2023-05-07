package Baetube_backEnd.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.service.playlist.PlaylistChannelService;
import Baetube_backEnd.service.playlist.PlaylistDeleteItemService;
import Baetube_backEnd.service.playlist.PlaylistDeleteService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
import Baetube_backEnd.service.playlist.PlaylistUpdateService;

public class RestPlaylistControllerTest
{
	@Spy
	@InjectMocks
	private RestPlaylistController restPlaylistController;
	@Spy
	@InjectMocks
	private PlaylistChannelService playlistChannelService;
	@Spy
	@InjectMocks
	private PlaylistInsertService playlistInsertService;
	@Spy
	@InjectMocks
	private PlaylistDeleteService playlistDeleteService;
	@Spy
	@InjectMocks
	private PlaylistUpdateService playlistUpdateService;
	@Spy
	@InjectMocks
	private PlaylistInsertItemService playlistInsertItemService;
	@Spy
	@InjectMocks
	private PlaylistDeleteItemService playlistDeleteItemService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private Playlist correctPlaylist;
	private Playlist wrongPlaylist;
	private String correctContent;
	private String wrongContent;

	@Before
	public void setUp() throws JsonProcessingException
	{
		restPlaylistController = spy(new RestPlaylistController());
		playlistChannelService = spy(new PlaylistChannelService());
		playlistInsertService = spy(new PlaylistInsertService());
		playlistDeleteService = spy(new PlaylistDeleteService());
		playlistUpdateService = spy(new PlaylistUpdateService());
		playlistInsertItemService = spy(new PlaylistInsertItemService());
		playlistDeleteItemService = spy(new PlaylistDeleteItemService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restPlaylistController).build();
		objectMapper = new ObjectMapper();
		
		correctPlaylist = new Playlist(1, 1, "test", 1, 0, "test");
		wrongPlaylist = new Playlist(2, 2, "test2", 1, 0, "test2");
		correctContent = objectMapper.writeValueAsString(correctPlaylist);
		wrongContent = objectMapper.writeValueAsString(wrongPlaylist);
	}
	@Ignore
	@Test
	public void playlistChannelTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		Channel wrongChannel = new Channel(2, 2, 0, 0, "test2", "test2", "test2", null);
		
		String correctContent = objectMapper.writeValueAsString(correctChannel);
		String wrongContent = objectMapper.writeValueAsString(wrongChannel);
		
		ArrayList<Playlist> playlistList = new ArrayList<>();
		playlistList.add(correctPlaylist);
		
		when(playlistMapper.selectByChannelId(1)).thenReturn(playlistList);
		when(playlistMapper.selectByChannelId(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/playlist/channel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/playlist/channel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void playlistInsertTest() throws Exception
	{
		mockMvc.perform(post("/api/playlist/insert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(playlistMapper, atLeastOnce()).insert(any());
	}
	
	@Test
	public void playlistDeleteTest() throws Exception
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(correctPlaylist);
		when(playlistMapper.selectByPlaylistId(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/playlist/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(playlistMapper, atLeastOnce()).delete(any());
		
		mockMvc.perform(post("/api/playlist/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void playlistUpdateTest() throws Exception
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(correctPlaylist);
		when(playlistMapper.selectByPlaylistId(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/playlist/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(playlistMapper, atLeastOnce()).update(any(), any());
		
		mockMvc.perform(post("/api/playlist/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void playlistInsertItemTest() throws Exception
	{
		PlaylistItem correctPlaylistItem = new PlaylistItem(1, 1);
		PlaylistItem wrongPlaylistItem = new PlaylistItem(2, 2);
		
		String correctContent = objectMapper.writeValueAsString(correctPlaylistItem);
		String wrongContent = objectMapper.writeValueAsString(wrongPlaylistItem);
		
		when(playlistMapper.selectPlaylistItem(1, 1)).thenReturn(null);
		when(playlistMapper.selectPlaylistItem(2, 2)).thenReturn(wrongPlaylistItem);
		
		mockMvc.perform(post("/api/playlist/insert_item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(playlistMapper, atLeastOnce()).insertItem(any());
		
		mockMvc.perform(post("/api/playlist/insert_item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void playlistDeleteItemTest() throws Exception
	{
		PlaylistItem correctPlaylistItem = new PlaylistItem(1, 1);
		PlaylistItem wrongPlaylistItem = new PlaylistItem(2, 2);
		
		String correctContent = objectMapper.writeValueAsString(correctPlaylistItem);
		String wrongContent = objectMapper.writeValueAsString(wrongPlaylistItem);
		
		when(playlistMapper.selectPlaylistItem(1, 1)).thenReturn(correctPlaylistItem);
		when(playlistMapper.selectPlaylistItem(2, 2)).thenReturn(null);
		
		mockMvc.perform(post("/api/playlist/delete_item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(playlistMapper, atLeastOnce()).deleteItem(any());
		
		mockMvc.perform(post("/api/playlist/delete_item")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
