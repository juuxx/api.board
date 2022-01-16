package com.project.callbus.domain.board.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.web.dto.BoardDto;
import com.project.callbus.web.dto.BoardListDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Entity
public class Board {

	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member writer;

	@Lob
	private String contents;

	@OneToMany(mappedBy = "board")
	private Set<Like> likes = new HashSet<>();


	public BoardListDto fromEntity(String memberId) {
		return BoardListDto.builder()
						.boardId(id)
						.title(title)
						.member(writer)
						.likeCount(likes.size())
						.clickedLike(clickedLike(memberId))
						.build();
	}

	private boolean clickedLike(String memberId) {
		return likes.stream()
					.anyMatch(v -> v.memberId().equals(memberId));
	}

	public BoardDto toBoardDto() {
		return new BoardDto();
	}
}
