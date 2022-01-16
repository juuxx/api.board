package com.project.callbus.domain.board.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.project.callbus.domain.board.repository.BoardRepository;

@DataJpaTest
class BoardTest {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void name() {
		//given

		//when

		//then
	}
}