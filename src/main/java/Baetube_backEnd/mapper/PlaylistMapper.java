package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.PlaylistItem;

public interface PlaylistMapper
{
	public void insert(@Param("playlist") Playlist playlist);
	public void delete(@Param("playlist") Playlist playlist);
	public List<Playlist> selectByChannelId(Integer channelId);
	public Playlist selectByPlaylistId(Integer playlistId);
	public PlaylistItem selectPlaylistItem(@Param("playlistItem") PlaylistItem playlistItem);
	public void update(@Param("oldPlaylist") Playlist oldPlaylist, @Param("newPlaylist") Playlist newPlaylist);
	
	public void insertItem(@Param("playlistItem") PlaylistItem playlistItem);
	public void deleteItem(@Param("playlistItem") PlaylistItem playlistItem);
	
	public void updateVideoCount(Integer playlistId, Integer value);
}
