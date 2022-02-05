package com.project.callbus.web.dto;

import java.util.HashSet;
import java.util.Set;

import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BoardDto {
	private String title;
	private Member writer;
	private String contents;
	private Set<Like> likes = new HashSet<>();

}
