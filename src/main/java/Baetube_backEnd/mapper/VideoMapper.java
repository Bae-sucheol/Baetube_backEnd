package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.dto.Video;

public interface VideoMapper
{
	public void insert(@Param("video") Video video);
	public List<Video> selectMainVideo(@Param("userId") Integer userId);
	public List<Video> selectSubscribeVideo(@Param("channelId") Integer channelId);
	public List<Video> selectChannelVideo(@Param("channelId") Integer channelId);
	public List<Video> selectHistoryVideo(@Param("userId") Integer userId);
	public List<Video> selectPlaylistVideo(@Param("playlistId") Integer playlistId);
	public List<Video> selectRelatedVideo(@Param("videoId") Integer videoId);
	public Video selectByVideoId(@Param("videoId") Integer videoId);
	public void update(@Param("oldVideo") Video oldVideo, @Param("newVideo") Video newVideo);
	public void updateViews(@Param("videoId") Integer videoId, @Param("value") Integer value);
	public Video selectByThumbnail(@Param("thumbnail") String thumbnail);
}
