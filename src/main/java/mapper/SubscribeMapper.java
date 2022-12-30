package mapper;

import dto.Subscribers;

public interface SubscribeMapper
{
	public void subscribe(Integer channelId, Integer subscriberId);
	public void unSubscribe(Integer channelId, Integer subscriberId);
	public Subscribers select(Integer channelId, Integer subscriberId);
}
