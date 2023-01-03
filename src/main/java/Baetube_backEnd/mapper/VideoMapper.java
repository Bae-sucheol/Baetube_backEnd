package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.dto.Video;

public interface VideoMapper
{
	public void insert(@Param("video") Video video);
	public List<Video> selectMainVideo(Integer userId);
	public List<Video> selectSubscribeVideo(Integer channelId);
	public List<Video> selectChannelVideo(Integer channelId);
	public List<Video> selectHistoryVideo(Integer userId);
	public List<Video> selectPlaylistVideo(Integer playlistId);
	public Video selectByVideoId(Integer videoId);
	public void update(@Param("oldVideo") Video oldVideo, @Param("newVideo") Video newVideo);
	public void updateViews(Integer videoId, Integer value);
}
