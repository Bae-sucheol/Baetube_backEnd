package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.User;
import dto.Video;

public interface VideoMapper
{
	public void insert(@Param("video") Video video);
	public List<Video> selectMainVideo(Integer userId);
	public List<Video> selectSubscribeVideo(Integer userId);
	public List<Video> selectChannelVideo(Integer chennlId);
	public List<Video> selectHistoryVideo(Integer userId);
	public void delete(Integer videoId);
	public void update(@Param("oldVideo") Video oldVideo, @Param("newVideo") Video newVideo);
	public void updateLike(Integer videoId, Integer value);
	public void updateHate(Integer videoId, Integer value);
	public void updateReplyCount(Integer videoId, Integer value);
	public void updateViews(Integer videoId, Integer value);
}
