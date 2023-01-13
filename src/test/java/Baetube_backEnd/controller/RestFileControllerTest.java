package Baetube_backEnd.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.service.file.FileUploadService;

public class RestFileControllerTest
{
	@Spy
	@InjectMocks
	private RestFileController restFileController;
	@Spy
	@InjectMocks
	private FileUploadService fileUploadService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		restFileController = spy(new RestFileController());
		fileUploadService = spy(new FileUploadService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restFileController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Ignore
	@Test
	public void uploadImageTest() throws Exception
	{
		String originPath = Paths.get("G:", "baetube", "test", "testImage.jpg").toString();
		String filePath = "testImage.jpg";
		FileInputStream fileInputStream = new FileInputStream(originPath);
		MockMultipartFile file = new MockMultipartFile("file", filePath, "jpg", fileInputStream);
			
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "image")
				.param("purpose", "profile")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "image")
				.param("purpose", "thumbnail")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "image")
				.param("purpose", "arts")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "image")
				.param("purpose", "community")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "test")
				.param("purpose", "community")
				.param("id", "1"))
		.andExpect(status().isConflict())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "image")
				.param("purpose", "test")
				.param("id", "1"))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Ignore
	@Test
	public void uploadVideoTest() throws Exception
	{
		String originPath = Paths.get("G:", "baetube", "test", "testVideo.mp4").toString();
		String filePath = "testVideo.mp4";
		FileInputStream fileInputStream = new FileInputStream(originPath);
		MockMultipartFile file = new MockMultipartFile("file", filePath, "mp4", fileInputStream);
			
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "video")
				.param("purpose", "video")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "test")
				.param("purpose", "video")
				.param("id", "1"))
		.andExpect(status().isConflict())
		.andDo(print());
		
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "video")
				.param("purpose", "test")
				.param("id", "1"))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void uploadVideo2Test() throws Exception
	{
		String originPath = Paths.get("G:", "baetube", "test", "testVideo.mp4").toString();
		String filePath = "testVideo.mp4";
		FileInputStream fileInputStream = new FileInputStream(originPath);
		MockMultipartFile file = new MockMultipartFile("file", filePath, "mp4", fileInputStream);
			
		mockMvc.perform(multipart("/api/file/upload")
				.file(file)
				.param("type", "video")
				.param("purpose", "video")
				.param("id", "1"))
		.andExpect(status().isOk())
		.andDo(print());

	}

}
