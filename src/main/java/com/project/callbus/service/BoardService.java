package com.project.callbus.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.board.repository.BoardRepository;
import com.project.callbus.domain.like.entity.Like;
import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.domain.member.repository.MemberRepository;
import com.project.callbus.web.dto.BoardDto;
import com.project.callbus.web.dto.BoardListDto;
import com.project.callbus.web.dto.RequestBoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public List<BoardListDto> findBoardList(String id) {
		List<Board> all = boardRepository.findAll();
		return all.stream()
			.map(v -> v.fromEntity(id))
			.collect(Collectors.toList());
	}

	@Transactional
	public BoardDto findBoardById(Long boardId) {
		Board board = findBoardByBoardId(boardId);
		return board.toBoardDto();
	}

	@Transactional
	public void writeBoard(RequestBoardDto requestBoardDto) {
		BoardDto boardDto = toBoardDto(requestBoardDto);
		Board board = Board.toEntity(boardDto);
		boardRepository.save(board);
	}

	@Transactional
	public void updateBoard(Long boardId, BoardDto boardDto) {
		Board board = findBoardByBoardId(boardId);
		board.update(boardDto);
		boardRepository.save(board);
	}

	@Transactional
	public void deleteBoard(Long boardId) {
		Board board = findBoardByBoardId(boardId);
		boardRepository.delete(board);
	}

	@Transactional
	public void likedBoard(Long boardId, String memberId) {
		Board board = findBoardByBoardId(boardId);
		Member member = findMemberByMemberId(memberId);

		Set<Like> likes = findBoardByBoardId(boardId).getLikes();
		likedProcess(memberId, board, member, likes);

		board.updateLike(likes);
		boardRepository.save(board);
	}

	private BoardDto toBoardDto(RequestBoardDto requestBoardDto) {
		Member member = findMemberByMemberId(requestBoardDto.getWriter());
		return new BoardDto(requestBoardDto.getTitle(), member, requestBoardDto.getContents(), new HashSet<>());
	}

	private Member findMemberByMemberId(String writer) {
		return memberRepository.findByAccountId(writer).orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
	}

	private Board findBoardByBoardId(Long boardId) {
		return boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("잘못된 boardId 입니다."));
	}

	private void likedProcess(String memberId, Board board, Member member, Set<Like> likes) {
		boolean myPushedLike = false;
		for (Like like : likes) {
			myPushedLike = removeLike(memberId, likes, like);
		}

		if (!myPushedLike) {
			addLike(board, member, likes);
		}
	}

	private boolean removeLike(String memberId, Set<Like> likes, Like like) {
		boolean pushedLike = false;
		if (memberId.equals(like.memberId())) {
			pushedLike = true; //이미 좋아요 누른 상태
			likes.remove(like);
		}
		return pushedLike;
	}

	private void addLike(Board board, Member member, Set<Like> likes) {
		likes.add(new Like(board, member));
	}

}
