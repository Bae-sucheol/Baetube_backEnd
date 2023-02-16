package Baetube_backEnd;

import java.util.UUID;

public class UUIDUtil
{
	/*
	 * 받은 파일의 파일명을 그대로 사용하는 것은 좋지 않다고 판단.
	 * 중복되지 않도록 TimeStamp + Hash를 사용하는 방법도 있지만
	 * uuid를 사용하기로 했음.
	 */
	public static String createUUID()
	{
		String uuid = UUID.randomUUID().toString();
	
		return uuid;
	}
	
	public static String getPrefix(String fileName)
	{
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		return prefix;
	}
	
}
