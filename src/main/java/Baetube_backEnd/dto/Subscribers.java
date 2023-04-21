package Baetube_backEnd.dto;

import java.sql.Timestamp;

public class Subscribers
{
	private Integer channelId;
	private Integer subscriberId;
	private Timestamp date;
	
	public Subscribers()
	{
		super();
	}

	public Subscribers(Integer channelId, Integer subscriberId)
	{
		super();
		this.channelId = channelId;
		this.subscriberId = subscriberId;
	}
	
	
	
	public Subscribers(Integer channelId, Integer subscriberId, Timestamp date)
	{
		super();
		this.channelId = channelId;
		this.subscriberId = subscriberId;
		this.date = date;
	}

	// getter
	public Integer getChannelId()
	{
		return channelId;
	}

	public Integer getSubscriberId()
	{
		return subscriberId;
	}
	
	public Timestamp getDate()
	{
		return date;
	}

	// setter
	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}

	public void setSubscriberId(Integer subscriberId)
	{
		this.subscriberId = subscriberId;
	}

	public void setDate(Timestamp date)
	{
		this.date = date;
	}
	
}
