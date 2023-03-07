package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;

public interface PlaylistMapper
{
	public void insert(@Param("playlist") Playlist playlist);
	public void delete(@Param("playlist") Playlist playlist);
	public List<Playlist> selectByChannelId(@Param("channelId") Integer channelId);
	public Playlist selectByPlaylistId(@Param("playlistId") Integer playlistId);
	public PlaylistItem selectPlaylistItem(@Param("playlistId") Integer playlistId, @Param("videoId") Integer videoId);
	public Integer selectPlaylistItemCount(@Param("playlistId") Integer playlistId, @Param("playlistItem") List<PlaylistItem> playlistItem);
	public void update(@Param("oldPlaylist") Playlist oldPlaylist, @Param("newPlaylist") Playlist newPlaylist);
	
	public void insertItem(@Param("playlistItem") List<PlaylistItem> playlistItem);
	public void deleteItem(@Param("playlistItem") PlaylistItem playlistItem);
	
	public void updateVideoCount(@Param("playlistId") Integer playlistId, @Param("value") Integer value);
}
