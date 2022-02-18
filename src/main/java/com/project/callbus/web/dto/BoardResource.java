package com.project.callbus.web.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardResource extends RepresentationModel<BoardResource> {
	private String title;
	private Member writer;
	private String contents;
	private Set<Like> likes = new HashSet<>();

	public BoardResource(Board board) {
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.contents = board.getContents();
		this.likes = board.getLikes();
	}

}
