package controllers.auth;


import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

//LoginContext����javax.security.auth.login�����һ���࣬��������������֤����(subjects)�ķ�����
//LoginContext������ø���ʵ�ֺ�ִ����֤��LoginModules��
public class MyClient
{
	public static void main(String[] args)
	{
		LoginContext context = null;
		try
		{
			// �������ļ�example.conf�ʵ����"WeatherLogin"���Ǳ�MyClient.java�����������ʵ������֡�
			context = new LoginContext("WeatherLogin", new MyCallbackHandler());
		} catch (LoginException le)
		{
			System.err.println("LoginContext cannot be created. "
					+ le.getMessage());
			System.exit(-1);
		} catch (SecurityException se)
		{
			System.err.println("LoginContext cannot be created. "
					+ se.getMessage());
		}
		try
		{
			context.login();
		} catch (LoginException le)
		{
			System.out.println("Authentication failed. " + le.getMessage());
			System.exit(-1);
		}
		System.out.println("authentication succeeded.");
		System.exit(-1);

	}
}