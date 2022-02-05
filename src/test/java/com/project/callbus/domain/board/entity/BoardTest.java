package com.project.callbus.domain.board.entity;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import com.project.callbus.domain.board.repository.BoardRepository;
import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.domain.member.repository.MemberRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BoardTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Commit
	void name() {
		//given
		Member member = memberRepository.findByAccountId("user124").get();

		Board board = Board.builder().title("제목").contents("내용").writer(member).build();
		Set<Like> likes = new HashSet<>();
		likes.add(new Like(board, member));

		board.addLikes(likes);

		//when
		boardRepository.save(board);
		//then
	}
}