package mapper;

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
	public User selectByEmail(String email);
	public void insert(@Param("user") User user);
	public void update(@Param("oldUser") User oldUser, @Param("newUser") User newUser);
	public void changePassword(String email, String newPassword);
	public void delete(Integer userId);
}
