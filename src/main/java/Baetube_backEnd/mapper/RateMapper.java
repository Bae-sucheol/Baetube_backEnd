package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Rate;

public interface RateMapper
{
	public void insert(@Param("rate") Rate rate);
}
