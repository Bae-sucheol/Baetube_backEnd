package Baetube_backEnd.dto;

public class DeleteHistoryRequest
{
	private Integer userId;
	private Integer videoId;
	
	public DeleteHistoryRequest(Integer userId, Integer videoId)
	{
		super();
		this.userId = userId;
		this.videoId = videoId;
	}

	// setter
	public Integer getUserId()
	{
		return userId;
	}

	public Integer getVideoId()
	{
		return videoId;
	}
	
	// getter
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}
	
	
}
