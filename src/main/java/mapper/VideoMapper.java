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
}
