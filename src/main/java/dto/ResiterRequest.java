package dto;

public class ResiterRequest
{
	private String password;
	private String confirmPassword;
    private String name;
    private String email;
    private String birth;
    private String phone;
    private String address;
    private boolean gender;
    
    // getter
	public String getPassword()
	{
		return password;
	}
	
	public String getConfirmPassword()
	{
		return confirmPassword;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getBirth()
	{
		return birth;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public boolean isGender()
	{
		return gender;
	}
	
	// setter
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setBirth(String birth)
	{
		this.birth = birth;
	}
	
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setGender(boolean gender)
	{
		this.gender = gender;
	}
    
}
