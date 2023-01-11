package Baetube_backEnd.dto;

public class VideoViewRequest
{
	private Integer videoId;
	private Integer userId;
	private Integer value;
	
	public VideoViewRequest()
	{
		super();
	}

	public VideoViewRequest(Integer videoId, Integer userId, Integer value)
	{
		super();
		this.videoId = videoId;
		this.userId = userId;
		this.value = value;
	}
	
	public Integer getVideoId()
	{
		return videoId;
	}
	public Integer getUserId()
	{
		return userId;
	}
	public Integer getValue()
	{
		return value;
	}
	
	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public void setValue(Integer value)
	{
		this.value = value;
	}
	
}
