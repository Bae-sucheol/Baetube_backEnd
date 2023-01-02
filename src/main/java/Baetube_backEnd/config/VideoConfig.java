package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.ChannelVideoRequestService;
import Baetube_backEnd.service.HistoryVideoRequestService;
import Baetube_backEnd.service.MainVideoRequestService;
import Baetube_backEnd.service.PlaylistVideoRequestService;
import Baetube_backEnd.service.SubscriveVideoRequestService;
import Baetube_backEnd.service.VideoInsertService;
import Baetube_backEnd.service.VideoUpdateService;
import Baetube_backEnd.service.VideoUpdateViewService;

@Configuration
public class VideoConfig
{
	@Bean
	public VideoInsertService videoInsertService()
	{
		return new VideoInsertService();
	}
	
	@Bean
	public VideoUpdateService videoUpdateService()
	{
		return new VideoUpdateService();
	}
	
	@Bean
	public VideoUpdateViewService videoUpdateViewService()
	{
		return new VideoUpdateViewService();
	}
	
	@Bean 
	public MainVideoRequestService mainVideoRequestService()
	{
		return new MainVideoRequestService();
	}
	
	@Bean
	public SubscriveVideoRequestService subscriveVideoRequestService()
	{
		return new SubscriveVideoRequestService();
	}
	
	@Bean
	public ChannelVideoRequestService channelVideoRequestService()
	{
		return new ChannelVideoRequestService();
	}
	
	@Bean
	public HistoryVideoRequestService historyVideoRequestService()
	{
		return new HistoryVideoRequestService();
	}
	
	@Bean
	public PlaylistVideoRequestService playlistVideoRequestService()
	{
		return new PlaylistVideoRequestService();
	}
}
