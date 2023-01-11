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
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.User;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.dto.VideoViewRequest;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.video.ChannelVideoRequestService;
import Baetube_backEnd.service.video.HistoryVideoRequestService;
import Baetube_backEnd.service.video.MainVideoRequestService;
import Baetube_backEnd.service.video.PlaylistVideoRequestService;
import Baetube_backEnd.service.video.SubscribeVideoRequestService;
import Baetube_backEnd.service.video.VideoInsertService;
import Baetube_backEnd.service.video.VideoUpdateService;
import Baetube_backEnd.service.video.VideoViewService;

public class RestVideoControllerTest
{
	@Spy
	@InjectMocks
	private RestVideoController restVideoController;
	@Spy
	@InjectMocks
	private ChannelVideoRequestService channelVideoRequestService;
	@Spy
	@InjectMocks
	private HistoryVideoRequestService historyVideoRequestService;
	@Spy
	@InjectMocks
	private MainVideoRequestService mainVideoRequestService;
	@Spy
	@InjectMocks
	private PlaylistVideoRequestService playlistVideoRequestService;
	@Spy
	@InjectMocks
	private SubscribeVideoRequestService subscribeVideoRequestService;
	@Spy
	@InjectMocks
	private VideoInsertService videoInsertService;
	@Spy
	@InjectMocks
	private VideoUpdateService videoUpdateService;
	@Spy
	@InjectMocks
	private VideoViewService videoViewService;
	
	@Mock
	private VideoMapper videoMapper;
	@Mock
	private PlaylistMapper playlistMapper;
	@Mock
	private HistoryMapper historyMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;

	@Before
	public void setUp()
	{
		restVideoController = spy(new RestVideoController());
		channelVideoRequestService = spy(new ChannelVideoRequestService());
		historyVideoRequestService = spy(new HistoryVideoRequestService());
		mainVideoRequestService = spy(new MainVideoRequestService());
		playlistVideoRequestService = spy(new PlaylistVideoRequestService());
		subscribeVideoRequestService = spy(new SubscribeVideoRequestService());
		videoInsertService = spy(new VideoInsertService());
		videoUpdateService = spy(new VideoUpdateService());
		videoViewService = spy(new VideoViewService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restVideoController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void channelVideoRequestTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		Channel wrongChannel = new Channel(2, 2, 0, 0, "test2", "test2", "test2", null);
		
		String correctContent = objectMapper.writeValueAsString(correctChannel);
		String wrongContent = objectMapper.writeValueAsString(wrongChannel);
		
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(new Video());
		
		when(videoMapper.selectChannelVideo(1)).thenReturn(videoList);
		when(videoMapper.selectChannelVideo(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/channel_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/video/channel_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void historyVideoRequestTest() throws Exception
	{
		User correctUser = new User(1, "test", "test", "test", 1, null, "test", "test", "test", null);
		User wrongUser = new User(2, "test2", "test2", "test2", 1, null, "test2", "test2", "test2", null);
		
		String correctContent = objectMapper.writeValueAsString(correctUser);
		String wrongContent = objectMapper.writeValueAsString(wrongUser);
		
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(new Video());
		
		when(videoMapper.selectHistoryVideo(1)).thenReturn(videoList);
		when(videoMapper.selectHistoryVideo(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/history_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/video/history_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void mainVideoRequestTest() throws Exception
	{
		User correctUser = new User(1, "test", "test", "test", 1, null, "test", "test", "test", null);
		User wrongUser = new User(2, "test2", "test2", "test2", 1, null, "test2", "test2", "test2", null);
		
		String correctContent = objectMapper.writeValueAsString(correctUser);
		String wrongContent = objectMapper.writeValueAsString(wrongUser);
		
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(new Video());
		
		when(videoMapper.selectMainVideo(1)).thenReturn(videoList);
		when(videoMapper.selectMainVideo(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/main_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/video/main_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void playlistVideoRequestTest() throws Exception
	{
		Playlist correctPlaylist = new Playlist(1, 1, "test", 1, 0, "test");
		Playlist wrongPlaylist = new Playlist(2, 2, "test2", 1, 0, "test2");
		
		String correctContent = objectMapper.writeValueAsString(correctPlaylist);
		String wrongContent = objectMapper.writeValueAsString(wrongPlaylist);
		
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(new Video());
		
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(correctPlaylist);
		when(playlistMapper.selectByPlaylistId(2)).thenReturn(null);
		when(videoMapper.selectPlaylistVideo(1)).thenReturn(videoList);
		when(videoMapper.selectPlaylistVideo(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/playlist_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/video/playlist_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void subscribeVideoRequestTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		Channel wrongChannel = new Channel(2, 2, 0, 0, "test2", "test2", "test2", null);
		
		String correctContent = objectMapper.writeValueAsString(correctChannel);
		String wrongContent = objectMapper.writeValueAsString(wrongChannel);
		
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(new Video());
		
		when(videoMapper.selectSubscribeVideo(1)).thenReturn(videoList);
		when(videoMapper.selectSubscribeVideo(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/subscribe_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/video/subscribe_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void videoInsertTest() throws Exception
	{
		Video video = new Video(1, 1, "test", "test", "test", "test", 1, 1);
		String content = objectMapper.writeValueAsString(video);

		mockMvc.perform(post("/api/video/insert")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(videoMapper, atLeastOnce()).insert(any());
	}
	
	@Test
	public void videoUpdateTest() throws Exception
	{
		Video correctVideo = new Video(1, 1L, 1, "test", 1, "test", "test", "test", "test", 1, 0, 0, 0, 0,
				null, 1, "test", null, 1, 1);
		Video wrongVideo = new Video(2, 2L, 2, "test", 2, "test", "test", "test", "test", 1, 0, 0, 0, 0,
				null, 1, "test", null, 1, 1);
		
		String correctContent = objectMapper.writeValueAsString(correctVideo);
		String wrongContent = objectMapper.writeValueAsString(wrongVideo);
		
		when(videoMapper.selectByVideoId(1)).thenReturn(correctVideo);
		when(videoMapper.selectByVideoId(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/video/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(videoMapper, atLeastOnce()).update(any(), any());
		
		mockMvc.perform(post("/api/video/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}

	@Test
	public void videoViewTest() throws Exception
	{
		VideoViewRequest correctVideoViewRequest = new VideoViewRequest(1, 1, 1);
		VideoViewRequest wrongVideoViewRequest = new VideoViewRequest(2, 2, 1);
		
		String correctContent = objectMapper.writeValueAsString(correctVideoViewRequest);
		String wrongContent = objectMapper.writeValueAsString(wrongVideoViewRequest);
		
		when(videoMapper.selectByVideoId(1)).thenReturn(new Video());
		when(videoMapper.selectByVideoId(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/video/view_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(videoMapper, atLeastOnce()).updateViews(any(), any());
		verify(historyMapper, atLeastOnce()).insert(any(), any());
		
		mockMvc.perform(get("/api/video/view_video")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
