package com.vam.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import dto.User;

/*
 * userDAO 대신 사용하는 것으로 보다 최신의 방법.
 * 인터페이스로서 메소드만 지정해주고 세부 내용은 Mapper.xml에서 작성한다.
 */

public interface UserMapper
{
	@Select("SELECT now() FROM user")
	public String getTime();
	
	public String getTime2();
	
	public String selectName(String email);
}
