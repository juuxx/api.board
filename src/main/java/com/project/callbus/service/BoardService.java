package com.project.callbus.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.board.repository.BoardRepository;
import com.project.callbus.web.dto.BoardDto;
import com.project.callbus.web.dto.BoardListDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	@Transactional
	public List<BoardListDto> findBoardList(String id) {
		List<Board> all = boardRepository.findAll();
		return all.stream()
				.map(v -> v.fromEntity(id))
				.collect(Collectors.toList());
	}

	@Transactional
	public BoardDto findBoardById(Long boardId) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("잘못된 boardId 입니다."));

		return board.toBoardDto();
	}
}
