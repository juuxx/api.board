package com.project.callbus.domain.board.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.project.callbus.web.dto.BoardResource;
import com.project.callbus.web.dto.BoardListDto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

	@OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval=true) //좋아요 누를 때 merge여야만 boardId, memberId가 들어가는 이유가 뭘까
	private Set<Like> likes = new HashSet<>();

	@Builder
	public Board(String title, Member writer, String contents) {
		this.title = title;
		this.writer = writer;
		this.contents = contents;
	}

	public Board(String title, Member writer, String contents, Set<Like> likes) {
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.likes = likes;
	}

	public static Board toEntity(BoardResource boardResource) {
		return new Board(boardResource.getTitle(), boardResource.getWriter(), boardResource.getContents(), new HashSet<>());
	}

	public void addLikes(Set<Like> likes) {
		this.likes = likes;
	}

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

	// public BoardDto toBoardDto() {
	// 	return new BoardDto(title, writer, contents, likes);
	// }

	public void update(BoardResource boardResource) {
		title = boardResource.getTitle();
		contents = boardResource.getContents();
	}

	public void updateLike(Set<Like> newLikes) {
		likes = newLikes;
	}
}
