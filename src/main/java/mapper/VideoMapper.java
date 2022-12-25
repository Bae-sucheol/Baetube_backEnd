package mapper;

import org.apache.ibatis.annotations.Param;

import dto.User;
import dto.Video;

public interface VideoMapper
{
	public void insert(@Param("video") Video video);
}
