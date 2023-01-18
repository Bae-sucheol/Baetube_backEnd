package Baetube_backEnd.dto;

import java.lang.annotation.Annotation;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.Prefix;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * sql 반환형이 TinyInt Integer로 반환.
 * int 도 Integer 로 반환되며
 * DateTime은 TimeStamp로 변환되기 때문에 이부분에 대해서 해결법을 찾아야 할 것 같다.
 */
public class User implements UserDetails
{
	private Integer userId;
	@NotBlank
	@Email
	private String email;
    private String password;
    private String name;
    private Integer gender;
    private Timestamp birth;
    private String fcmToken;
    private String phone;
    private String address;
    private Timestamp regDate;
  
    private List<String> roles = new ArrayList<>();
    
   	@Override
   	public Collection<? extends GrantedAuthority> getAuthorities()
   	{
   		// TODO Auto-generated method stub
   		return this.roles.stream()
   				.map(SimpleGrantedAuthority::new)
   				.collect(Collectors.toList());
   	}

    /**
     * db에서 데이터를 받아올 때 필요한 생성자로 모든 속성을 인자로 받아서 사용한다.
     * @param userId
     * @param email
     * @param password
     * @param name
     * @param gender
     * @param birth
     * @param fcmToken
     * @param phone
     * @param address
     * @param regDate
     */
    public User(Integer userId, String email, String password, String name, Integer gender, Timestamp birth, String fcmToken,
			String phone, String address, Timestamp regDate)
	{
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.fcmToken = fcmToken;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
	}
    
    // db에서 데이터를 받아오는 용도 이외의 용도로 사용.
	public User(String email, String password, String name, Integer gender, Timestamp birth, String fcmToken,
			String phone, String address)
	{
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.fcmToken = fcmToken;
		this.phone = phone;
		this.address = address;
	}
	
	public User(Integer userId, String email, String password, String name, Integer gender, Timestamp birth,
			String fcmToken, String phone, String address, Timestamp regDate, List<String> roles)
	{
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.fcmToken = fcmToken;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
		this.roles = roles;
	}

	public User()
	{
		super();
	}

	// getter
    public int getUserId()
    {
        return userId;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public Timestamp getBirth()
    {
        return birth;
    }

    public String getFcmToken()
    {
        return fcmToken;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getAddress()
    {
        return address;
    }

    public Timestamp getRegDate()
    {
        return regDate;
    }

    public Integer getGender()
    {
        return gender;
    }

    // setter
    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setBirth(Timestamp birth)
    {
        this.birth = birth;
    }

    public void setFcmToken(String fcmToken)
    {
        this.fcmToken = fcmToken;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setRegDate(Timestamp regDate)
    {
        this.regDate = regDate;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

	@Override
	public String getUsername()
	{
		return email;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

	public String[] getRoles()
	{
		String str[] = new String[roles.size()];
		
		for(int i = 0; i < roles.size(); i++)
		{
			str[i] = roles.get(i);
		}
		
		return str;
	}

}
