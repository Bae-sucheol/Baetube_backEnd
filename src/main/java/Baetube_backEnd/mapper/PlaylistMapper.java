package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Playlist;

public interface PlaylistMapper
{
	public void insert(@Param("playlist") Playlist playlist);
	public void delete(Integer playlistId, Integer channelId);
	public List<Playlist> selectByChannelId(Integer channelId);
	public Playlist selectByPlaylistId(Integer playlistId);
	public void update(@Param("oldPlaylist") Playlist oldPlaylist, @Param("newPlaylist") Playlist newPlaylist);
	public void updateVideoCount(Integer playlistId, Integer value);
}
