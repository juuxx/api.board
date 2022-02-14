package com.project.callbus.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.callbus.service.MemberService;
import com.project.callbus.web.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/member")
	public void joinMember(@RequestBody MemberDto memberDto) {
		memberService.joinMember(memberDto);
	}
}
