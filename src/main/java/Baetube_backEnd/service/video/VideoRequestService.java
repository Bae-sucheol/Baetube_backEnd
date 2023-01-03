package Baetube_backEnd.service.video;

import java.util.List;

import Baetube_backEnd.dto.Video;

public interface VideoRequestService
{
	public List<Video> requestVideo(Integer id);
}
