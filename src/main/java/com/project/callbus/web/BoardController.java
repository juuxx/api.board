package com.project.callbus.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.callbus.common.config.security.dto.CustomUserDetail;
import com.project.callbus.service.BoardService;
import com.project.callbus.web.dto.BoardDto;
import com.project.callbus.web.dto.BoardListDto;
import com.project.callbus.web.dto.RequestBoardDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;

	//글 목록 조회
	@GetMapping("/api/v1/board")
	public ResponseEntity<List<BoardListDto>> boardList(@AuthenticationPrincipal CustomUserDetail customUserDetail){
		return ResponseEntity.ok(boardService.findBoardList(customUserDetail.getUsername()));
	}

	//글 조회
	@GetMapping("/api/v1/board/{boardId}")
	public ResponseEntity<BoardDto> board(@PathVariable Long boardId) {
		BoardDto boardDto = boardService.findBoardById(boardId);
		return ResponseEntity.ok(boardDto);
	}

	//글 쓰기
	@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	@PostMapping("/api/v1/board")
	public void crateBoard(@RequestBody RequestBoardDto requestBoardDto, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
		boardService.writeBoard(customUserDetail.getUsername(), requestBoardDto);
	}

	//글 수정
	@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	@PutMapping("/api/v1/board/{boardId}")
	public void updateBoard(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {
		boardService.updateBoard(boardId, boardDto);
	}

	//글 삭제
	@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	@DeleteMapping("/api/v1/board/{boardId}")
	public void deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
	}

	//좋아요 누르기
	@PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	@PostMapping("/api/v1/like/{boardId}/") //왜 헤더에 다른 아이디 넣었는데 인식을 못하지..
	public void likeBoard(@PathVariable Long boardId, @AuthenticationPrincipal CustomUserDetail customUserDetail){
		boardService.likedBoard(boardId, customUserDetail.getUsername());
	}
}
