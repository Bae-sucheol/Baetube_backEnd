package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.video.ChannelVideoRequestService;
import Baetube_backEnd.service.video.HistoryVideoRequestService;
import Baetube_backEnd.service.video.MainVideoRequestService;
import Baetube_backEnd.service.video.PlaylistVideoRequestService;
import Baetube_backEnd.service.video.RelatedVideoRequestService;
import Baetube_backEnd.service.video.SubscribeVideoRequestService;
import Baetube_backEnd.service.video.VideoInsertService;
import Baetube_backEnd.service.video.VideoUpdateService;
import Baetube_backEnd.service.video.VideoViewService;


@Configuration
public class VideoConfig
{
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
	public MainVideoRequestService mainVideoRequestService()
	{
		return new MainVideoRequestService();
	}
	
	@Bean
	public PlaylistVideoRequestService playlistVideoRequestService()
	{
		return new PlaylistVideoRequestService();
	}
	
	@Bean
	public SubscribeVideoRequestService subscribeVideoRequestService()
	{
		return new SubscribeVideoRequestService();
	}
	
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
	public VideoViewService videoViewService()
	{
		return new VideoViewService();
	}
	
	@Bean
	public RelatedVideoRequestService relatedVideoRequestService()
	{
		return new RelatedVideoRequestService();
	}
}
