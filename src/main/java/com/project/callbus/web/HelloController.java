package com.project.callbus.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/home")
	public String home(){
		return "도레미 바보 난 리액트 하는 중!";
	}
}
