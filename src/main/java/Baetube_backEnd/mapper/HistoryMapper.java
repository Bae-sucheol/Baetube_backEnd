package Baetube_backEnd.mapper;

public interface HistoryMapper
{
	public void insert(Integer userId, Integer videoId);
	public void delete(Integer userId, Integer videoId);
}
