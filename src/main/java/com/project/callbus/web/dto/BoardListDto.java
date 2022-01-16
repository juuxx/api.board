package com.project.callbus.web.dto;

import java.util.Set;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;

import javafx.scene.chart.ChartBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardListDto {
	private Long boardId;
	private String title;
	private Member member;
	private int likeCount;
	private boolean clickedLike;

	@Builder
	public BoardListDto(Long boardId, String title, Member member, int likeCount, boolean clickedLike) {
		this.boardId = boardId;
		this.title = title;
		this.member = member;
		this.likeCount = likeCount;
		this.clickedLike = clickedLike;
	}
}
