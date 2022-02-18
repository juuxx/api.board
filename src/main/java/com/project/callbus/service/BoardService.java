package com.project.callbus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.domain.board.repository.BoardRepository;
import com.project.callbus.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final MemberRepository memberRepository;

	// @Transactional
	// public List<BoardListDto> findBoardList(String id) {
	// 	List<Board> all = boardRepository.findAll();
	// 	return all.stream()
	// 		.map(v -> v.fromEntity(id))
	// 		.collect(Collectors.toList());
	// }
	//
	// @Transactional
	// public BoardResource findBoardById(Long boardId) {
	// 	Board board = findBoardByBoardId(boardId);
	// 	return board.toBoardDto();
	// }
	//
	// @Transactional
	// public void writeBoard(String memberId, RequestBoardDto requestBoardDto) {
	// 	BoardResource boardResource = toBoardDto(memberId, requestBoardDto);
	// 	Board board = Board.toEntity(boardResource);
	// 	boardRepository.save(board);
	// }
	//
	// @Transactional
	// public void updateBoard(Long boardId, BoardResource boardResource) {
	// 	Board board = findBoardByBoardId(boardId);
	// 	board.update(boardResource);
	// 	boardRepository.save(board);
	// }
	//
	// @Transactional
	// public void deleteBoard(Long boardId) {
	// 	Board board = findBoardByBoardId(boardId);
	// 	boardRepository.delete(board);
	// }
	//
	// @Transactional
	// public void likedBoard(Long boardId, String memberId) {
	// 	Board board = findBoardByBoardId(boardId);
	// 	Member member = findMemberByMemberId(memberId);
	//
	// 	Set<Like> likes = findBoardByBoardId(boardId).getLikes();
	// 	likedProcess(memberId, board, member, likes);
	//
	// 	board.updateLike(likes);
	// 	boardRepository.save(board);
	// }
	//
	// private BoardResource toBoardDto(String memberId, RequestBoardDto requestBoardDto) {
	// 	Member member = findMemberByMemberId(memberId);
	// 	return new BoardResource(requestBoardDto.getTitle(), member, requestBoardDto.getContents(), new HashSet<>());
	// }
	//
	// private Member findMemberByMemberId(String memberId) {
	// 	return memberRepository.findByAccountId(memberId).orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
	// }
	//
	// private Board findBoardByBoardId(Long boardId) {
	// 	return boardRepository.findById(boardId)
	// 		.orElseThrow(() -> new IllegalArgumentException("잘못된 boardId 입니다."));
	// }
	//
	// private void likedProcess(String memberId, Board board, Member member, Set<Like> likes) {
	// 	boolean myPushedLike = false;
	// 	for (Like like : likes) {
	// 		myPushedLike = removeLike(memberId, likes, like);
	// 	}
	//
	// 	if (!myPushedLike) {
	// 		addLike(board, member, likes);
	// 	}
	// }
	//
	// private boolean removeLike(String memberId, Set<Like> likes, Like like) {
	// 	boolean pushedLike = false;
	// 	if (memberId.equals(like.memberId())) {
	// 		pushedLike = true; //이미 좋아요 누른 상태
	// 		likes.remove(like);
	// 	}
	// 	return pushedLike;
	// }
	//
	// private void addLike(Board board, Member member, Set<Like> likes) {
	// 	likes.add(new Like(board, member));
	// }

	public Page<Board> getBoardList(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}
}
