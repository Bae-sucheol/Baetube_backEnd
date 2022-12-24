package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import dto.User;

/*
 * userDAO ��� ����ϴ� ������ ���� �ֽ��� ���.
 * �������̽��μ� �޼ҵ常 �������ְ� ���� ������ Mapper.xml���� �ۼ��Ѵ�.
 */

public interface UserMapper
{
	public User selectByEmail(@Param("email") String email);
	public void insert(@Param("user") User user);
}
