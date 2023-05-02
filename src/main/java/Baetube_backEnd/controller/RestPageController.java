package Baetube_backEnd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestPageController
{
	@GetMapping("api/address")
	public String getAddressPage()
	{
		return "kakaoAddress";
	}
}
