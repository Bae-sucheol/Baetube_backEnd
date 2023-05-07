package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.User;

/*
 * userDAO ��� ����ϴ� ������ ���� �ֽ��� ���.
 * �������̽��μ� �޼ҵ常 �������ְ� ���� ������ Mapper.xml���� �ۼ��Ѵ�.
 */

public interface UserMapper
{
	public User selectByEmail(@Param("email") String email);
	public void insert(@Param("user") User user);
	public void update(@Param("oldUser") User oldUser, @Param("newUser") User newUser);
	public void changePassword(@Param("email") String email, @Param("newPassword") String newPassword);
	public void delete(@Param("userId") Integer userId);
	public void updateRefreshToken(@Param("email") String email , @Param("refreshToken") String refreshToken);
	public void updateFCMToken(@Param("fcmToken") String fcmToken, @Param("email") String email);
	public String selectRefreshToken(@Param("email") String email);
}
