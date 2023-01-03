package Baetube_backEnd.dto;

public class VideoViewRequest
{
	private Integer videoId;
	private Integer value;
	
	public VideoViewRequest(Integer videoId, Integer value)
	{
		super();
		this.videoId = videoId;
		this.value = value;
	}
	
	public Integer getVideoId()
	{
		return videoId;
	}
	public Integer getValue()
	{
		return value;
	}
	
	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}
	public void setValue(Integer value)
	{
		this.value = value;
	}
	
}
