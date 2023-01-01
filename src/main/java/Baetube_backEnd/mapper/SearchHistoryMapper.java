package Baetube_backEnd.mapper;

public interface SearchHistoryMapper
{
	public void insert(Integer userId, String keywords);
	public void delete(Integer userId, String keywords);
}
