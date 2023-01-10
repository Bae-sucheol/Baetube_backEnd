package Baetube_backEnd.service.history;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.DeleteHistoryRequest;
import Baetube_backEnd.mapper.HistoryMapper;

public class HistoryDeleteService
{
	@Autowired
	private HistoryMapper historyMapper;
	
	public boolean deleteHistory(DeleteHistoryRequest request)
	{
		historyMapper.delete(request.getUserId(), request.getVideoId());
		
		return true;
	}
	
}
