package Baetube_backEnd;

import java.util.UUID;

public class UUIDUtil
{
	/*
	 * ���� ������ ���ϸ��� �״�� ����ϴ� ���� ���� �ʴٰ� �Ǵ�.
	 * �ߺ����� �ʵ��� TimeStamp + Hash�� ����ϴ� ����� ������
	 * uuid�� ����ϱ�� ����.
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
