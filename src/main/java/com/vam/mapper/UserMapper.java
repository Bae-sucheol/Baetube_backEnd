package com.vam.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import dto.User;

/*
 * userDAO ��� ����ϴ� ������ ���� �ֽ��� ���.
 * �������̽��μ� �޼ҵ常 �������ְ� ���� ������ Mapper.xml���� �ۼ��Ѵ�.
 */

public interface UserMapper
{
	@Select("SELECT now() FROM user")
	public String getTime();
	
	public String getTime2();
	
	public String selectName(String email);
}
