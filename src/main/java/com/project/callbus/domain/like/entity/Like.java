package com.project.callbus.domain.like.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.member.entity.Member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "board_like")
@Entity
public class Like {

	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;

	@Getter
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	public String memberId() {
		return this.member.getAccountId();
	}

}
