package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.vote.CancelVoteOptionService;
import Baetube_backEnd.service.vote.CastVoteOptionService;
import Baetube_backEnd.service.vote.VoteDeleteOptionMultiService;
import Baetube_backEnd.service.vote.VoteDeleteOptionService;
import Baetube_backEnd.service.vote.VoteDeleteService;
import Baetube_backEnd.service.vote.VoteInsertOptionMultiService;
import Baetube_backEnd.service.vote.VoteInsertOptionService;
import Baetube_backEnd.service.vote.VoteInsertService;
import Baetube_backEnd.service.vote.VoteSelectOptionService;
import Baetube_backEnd.service.vote.VoteUpdateOptionService;
import Baetube_backEnd.service.vote.VoteUpdateService;

@Configuration
public class VoteConfig
{
	@Bean
	public VoteDeleteOptionMultiService voteDeleteOptionMultiService()
	{
		return new VoteDeleteOptionMultiService();
	}
	
	@Bean
	public VoteDeleteOptionService voteDeleteOptionService()
	{
		return new VoteDeleteOptionService();
	}
	
	@Bean
	public VoteDeleteService voteDeleteService()
	{
		return new VoteDeleteService();
	}
	
	@Bean
	public VoteInsertOptionMultiService voteInsertOptionMultiService()
	{
		return new VoteInsertOptionMultiService();
	}
	
	@Bean
	public VoteInsertOptionService voteInsertOptionService()
	{
		return new VoteInsertOptionService();
	}
	
	@Bean
	public VoteInsertService voteInsertService()
	{
		return new VoteInsertService();
	}
	
	@Bean
	public VoteSelectOptionService voteSelectOptionService()
	{
		return new VoteSelectOptionService();
	}
	
	@Bean
	public VoteUpdateOptionService voteUpdateOptionService()
	{
		return new VoteUpdateOptionService();
	}
	
	@Bean
	public VoteUpdateService voteUpdateService()
	{
		return new VoteUpdateService();
	}
	
	@Bean
	public CastVoteOptionService castVoteOptionService()
	{
		return new CastVoteOptionService();
	}
	
	@Bean
	public CancelVoteOptionService cancelVoteOptionService()
	{
		return new CancelVoteOptionService();
	}
}
