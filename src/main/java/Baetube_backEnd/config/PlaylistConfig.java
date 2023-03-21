package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.playlist.PlaylistChannelService;
import Baetube_backEnd.service.playlist.PlaylistDeleteItemService;
import Baetube_backEnd.service.playlist.PlaylistDeleteLikeVideoService;
import Baetube_backEnd.service.playlist.PlaylistDeleteService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemMultiService;
import Baetube_backEnd.service.playlist.PlaylistInsertItemService;
import Baetube_backEnd.service.playlist.PlaylistInsertLikeVideoService;
import Baetube_backEnd.service.playlist.PlaylistInsertService;
import Baetube_backEnd.service.playlist.PlaylistUpdateService;

@Configuration
public class PlaylistConfig
{
	@Bean
	public PlaylistChannelService playlistChannelService()
	{
		return new PlaylistChannelService();
	}
	
	@Bean
	public PlaylistDeleteItemService playlistDeleteItemService()
	{
		return new PlaylistDeleteItemService();
	}
	
	@Bean
	public PlaylistDeleteService playlistDeleteService()
	{
		return new PlaylistDeleteService();
	}
	
	@Bean
	public PlaylistInsertItemService playlistInsertItemService()
	{
		return new PlaylistInsertItemService();
	}
	
	@Bean
	public PlaylistInsertService playlistInsertService()
	{
		return new PlaylistInsertService();
	}
	
	@Bean
	public PlaylistUpdateService playlistUpdateService()
	{
		return new PlaylistUpdateService();
	}
	
	@Bean
	public PlaylistInsertItemMultiService playlistInsertItemMultiService()
	{
		return new PlaylistInsertItemMultiService();
	}
	
	@Bean
	public PlaylistInsertLikeVideoService playlistInsertLikeVideoService()
	{
		return new PlaylistInsertLikeVideoService();
	}
	
	@Bean
	public PlaylistDeleteLikeVideoService playlistDeleteLikeVideoService()
	{
		return new PlaylistDeleteLikeVideoService();
	}
}
