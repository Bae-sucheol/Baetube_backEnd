package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Playlist;

public interface PlaylistMapper
{
	public void insert(@Param("playlist") Playlist playlist);
	public void delete(Integer playlistId, Integer channelId);
	public List<Playlist> selectByChannelId(Integer channelId);
	public void update(@Param("oldPlaylist") Playlist oldPlaylist, @Param("newPlaylist") Playlist newPlaylist);
	public void updateVideoCount(Integer playlistId, Integer value);
}
