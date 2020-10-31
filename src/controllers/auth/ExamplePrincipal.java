package controllers.auth;


import java.security.Principal;
 
// ExamplePrincipal չʾ��Principal�������������ӿڵ�һ��ʵ�֡� 
public class ExamplePrincipal implements Principal
{
	private final String name;
 
	public ExamplePrincipal(String name)
	{
		if (name == null)
		{
			throw new IllegalArgumentException("Null name");
		}
		this.name = name;
	}
 
	public String getName()
	{
		return name;
	}
 
	public String toString()
	{
		return "ExamplePrinciapl: " + name;
	}
 
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ExamplePrincipal))
			return false;
		ExamplePrincipal another = (ExamplePrincipal) obj;
		return name.equals(another.getName());
	}
 
	public int hasCode()
	{
		return name.hashCode();
	}
}