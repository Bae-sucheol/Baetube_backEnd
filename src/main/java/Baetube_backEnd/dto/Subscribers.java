package Baetube_backEnd.dto;

public class Subscribers
{
	private Integer channelId;
	private Integer subscriberId;
	
	public Subscribers(Integer channelId, Integer subscriberId)
	{
		super();
		this.channelId = channelId;
		this.subscriberId = subscriberId;
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
	
	// setter
	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}

	public void setSubscriberId(Integer subscriberId)
	{
		this.subscriberId = subscriberId;
	}
	
	
}
