package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class History
{
	private Integer userId;
	private Integer videoId;
	private Timestamp date;
	
	// constructor
	public History()
	{
		super();
	}
	
	public History(Integer userId, Integer videoId)
	{
		super();
		this.userId = userId;
		this.videoId = videoId;
	}
	
	public History(Integer userId, Integer videoId, Timestamp date)
	{
		super();
		this.userId = userId;
		this.videoId = videoId;
		this.date = date;
	}
	
	// getter
	public Integer getUserId()
	{
		return userId;
	}
	public Integer getVideoId()
	{
		return videoId;
	}
	public Timestamp getDate()
	{
		return date;
	}
	
	// setter
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	public void setVideoId(Integer videoId)
	{
		this.videoId = videoId;
	}
	public void setDate(Timestamp date)
	{
		this.date = date;
	}
	
	
}
