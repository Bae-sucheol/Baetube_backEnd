package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.User;

/*
 * userDAO 대신 사용하는 것으로 보다 최신의 방법.
 * 인터페이스로서 메소드만 지정해주고 세부 내용은 Mapper.xml에서 작성한다.
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
