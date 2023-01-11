package Baetube_backEnd.service.history;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.History;
import Baetube_backEnd.exception.NullHistoryException;
import Baetube_backEnd.mapper.HistoryMapper;

public class HistoryDeleteService
{
	@Autowired
	private HistoryMapper historyMapper;

	public boolean deleteHistory(History request)
	{
		History history = historyMapper.select(request.getUserId(), request.getVideoId());
		
		if(history == null)
		{
			throw new NullHistoryException();
		}
		
		historyMapper.delete(request.getUserId(), request.getVideoId());
		
		return true;
	}
	
}
