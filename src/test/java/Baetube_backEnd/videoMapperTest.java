package Baetube_backEnd;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class videoMapperTest
{
	@Autowired
	private VideoMapper videoMapper;
	
	@Ignore
	@Test
	public void insertVideoTest()
	{
		for (int i = 10; i < 20; i++)
		{
			Video video = new Video(1, "1234", 1, "1234", "테스트 비디오" + (i + 1), "1234",
					"1234", 1, 0, 0, 0, 0, new Timestamp(System.currentTimeMillis()),
					1, "1234");
			
			videoMapper.insert(video);
		}
	}
	
	@Test
	public void selectMainVideoTest()
	{
		List<Video> list = videoMapper.selectMainVideo(2);
		
		System.out.println("========== selectMainVideoTest ==========");
		
		for (int i = 0; i < list.size(); i++)
		{
			Video video = list.get(i);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" video_id : ");
			stringBuilder.append(video.getVideoId()).append("\n");
			stringBuilder.append(" channel_id : ");
			stringBuilder.append(video.getChannelId()).append("\n");
			stringBuilder.append(" url : ");
			stringBuilder.append(video.getUrl()).append("\n");
			stringBuilder.append(" public : ");
			stringBuilder.append(video.getVisible()).append("\n");
			stringBuilder.append(" thumbnail : ");
			stringBuilder.append(video.getThumbnail()).append("\n");
			stringBuilder.append(" title : ");
			stringBuilder.append(video.getTitle()).append("\n");
			stringBuilder.append(" info : ");
			stringBuilder.append(video.getInfo()).append("\n");
			stringBuilder.append(" location : ");
			stringBuilder.append(video.getLocation()).append("\n");
			stringBuilder.append(" age : ");
			stringBuilder.append(video.getAge()).append("\n");
			stringBuilder.append(" views : ");
			stringBuilder.append(video.getViews()).append("\n");
			stringBuilder.append(" like : ");
			stringBuilder.append(video.getLike()).append("\n");
			stringBuilder.append(" hate : ");
			stringBuilder.append(video.getHate()).append("\n");
			stringBuilder.append(" reply_count : ");
			stringBuilder.append(video.getReplyCount()).append("\n");
			stringBuilder.append(" date : ");
			stringBuilder.append(video.getDate()).append("\n");
			stringBuilder.append(" category_id : ");
			stringBuilder.append(video.getCategoryId()).append("\n");
			stringBuilder.append("\n").append("--------------------------------------").append("\n");
			
			System.out.println(stringBuilder.toString());
			
		}
	}
	
	@Test
	public void selectChannelVideoTest()
	{
		List<Video> list = videoMapper.selectChannelVideo(1);
		
		System.out.println("========== selectChannelVideoTest ==========");
		
		for (int i = 0; i < list.size(); i++)
		{
			Video video = list.get(i);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" video_id : ");
			stringBuilder.append(video.getVideoId()).append("\n");
			stringBuilder.append(" channel_id : ");
			stringBuilder.append(video.getChannelId()).append("\n");
			stringBuilder.append(" url : ");
			stringBuilder.append(video.getUrl()).append("\n");
			stringBuilder.append(" public : ");
			stringBuilder.append(video.getVisible()).append("\n");
			stringBuilder.append(" thumbnail : ");
			stringBuilder.append(video.getThumbnail()).append("\n");
			stringBuilder.append(" title : ");
			stringBuilder.append(video.getTitle()).append("\n");
			stringBuilder.append(" info : ");
			stringBuilder.append(video.getInfo()).append("\n");
			stringBuilder.append(" location : ");
			stringBuilder.append(video.getLocation()).append("\n");
			stringBuilder.append(" age : ");
			stringBuilder.append(video.getAge()).append("\n");
			stringBuilder.append(" views : ");
			stringBuilder.append(video.getViews()).append("\n");
			stringBuilder.append(" like : ");
			stringBuilder.append(video.getLike()).append("\n");
			stringBuilder.append(" hate : ");
			stringBuilder.append(video.getHate()).append("\n");
			stringBuilder.append(" reply_count : ");
			stringBuilder.append(video.getReplyCount()).append("\n");
			stringBuilder.append(" date : ");
			stringBuilder.append(video.getDate()).append("\n");
			stringBuilder.append(" category_id : ");
			stringBuilder.append(video.getCategoryId()).append("\n");
			stringBuilder.append("\n").append("--------------------------------------").append("\n");
			
			System.out.println(stringBuilder.toString());
			
		}
	}
	
	@Test
	public void selectHistoryVideoTest()
	{
		List<Video> list = videoMapper.selectHistoryVideo(2);
		
		System.out.println("========== selectHistoryVideoTest ==========");
		
		for (int i = 0; i < list.size(); i++)
		{
			Video video = list.get(i);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" video_id : ");
			stringBuilder.append(video.getVideoId()).append("\n");
			stringBuilder.append(" channel_id : ");
			stringBuilder.append(video.getChannelId()).append("\n");
			stringBuilder.append(" url : ");
			stringBuilder.append(video.getUrl()).append("\n");
			stringBuilder.append(" public : ");
			stringBuilder.append(video.getVisible()).append("\n");
			stringBuilder.append(" thumbnail : ");
			stringBuilder.append(video.getThumbnail()).append("\n");
			stringBuilder.append(" title : ");
			stringBuilder.append(video.getTitle()).append("\n");
			stringBuilder.append(" info : ");
			stringBuilder.append(video.getInfo()).append("\n");
			stringBuilder.append(" location : ");
			stringBuilder.append(video.getLocation()).append("\n");
			stringBuilder.append(" age : ");
			stringBuilder.append(video.getAge()).append("\n");
			stringBuilder.append(" views : ");
			stringBuilder.append(video.getViews()).append("\n");
			stringBuilder.append(" like : ");
			stringBuilder.append(video.getLike()).append("\n");
			stringBuilder.append(" hate : ");
			stringBuilder.append(video.getHate()).append("\n");
			stringBuilder.append(" reply_count : ");
			stringBuilder.append(video.getReplyCount()).append("\n");
			stringBuilder.append(" date : ");
			stringBuilder.append(video.getDate()).append("\n");
			stringBuilder.append(" category_id : ");
			stringBuilder.append(video.getCategoryId()).append("\n");
			stringBuilder.append("\n").append("--------------------------------------").append("\n");
			
			System.out.println(stringBuilder.toString());
			
		}
	}
	
	@Ignore
	@Test
	public void selectSubscribeVideoTest()
	{
		List<Video> list = videoMapper.selectSubscribeVideo(9);
		
		System.out.println("========== selectSubscribeVideoTest ==========");
		
		for (int i = 0; i < list.size(); i++)
		{
			Video video = list.get(i);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" video_id : ");
			stringBuilder.append(video.getVideoId()).append("\n");
			stringBuilder.append(" channel_id : ");
			stringBuilder.append(video.getChannelId()).append("\n");
			stringBuilder.append(" url : ");
			stringBuilder.append(video.getUrl()).append("\n");
			stringBuilder.append(" public : ");
			stringBuilder.append(video.getVisible()).append("\n");
			stringBuilder.append(" thumbnail : ");
			stringBuilder.append(video.getThumbnail()).append("\n");
			stringBuilder.append(" title : ");
			stringBuilder.append(video.getTitle()).append("\n");
			stringBuilder.append(" info : ");
			stringBuilder.append(video.getInfo()).append("\n");
			stringBuilder.append(" location : ");
			stringBuilder.append(video.getLocation()).append("\n");
			stringBuilder.append(" age : ");
			stringBuilder.append(video.getAge()).append("\n");
			stringBuilder.append(" views : ");
			stringBuilder.append(video.getViews()).append("\n");
			stringBuilder.append(" like : ");
			stringBuilder.append(video.getLike()).append("\n");
			stringBuilder.append(" hate : ");
			stringBuilder.append(video.getHate()).append("\n");
			stringBuilder.append(" reply_count : ");
			stringBuilder.append(video.getReplyCount()).append("\n");
			stringBuilder.append(" date : ");
			stringBuilder.append(video.getDate()).append("\n");
			stringBuilder.append(" category_id : ");
			stringBuilder.append(video.getCategoryId()).append("\n");
			stringBuilder.append("\n").append("--------------------------------------").append("\n");
			
			System.out.println(stringBuilder.toString());
			
		}
	}
	
}
