package com.project.callbus.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.callbus.service.BoardService;
import com.project.callbus.web.dto.BoardDto;
import com.project.callbus.web.dto.BoardListDto;
import com.project.callbus.web.dto.CustomUserDetail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;

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
	@PostMapping("/api/v1/board")
	public void crateBoard(@RequestBody BoardDto boardDto) {

	}

	//글 수정
	@PutMapping("/api/v1/board/{boardId}")
	public void updateBoard(@PathVariable Long boardId, @RequestBody BoardDto boardDto) {

	}

	//글 삭제
	@DeleteMapping("/api/v1/board/{boardId}")
	public void deleteBoard(@PathVariable Long boardId) {

	}

	//@PostMapping("/api/v1/like/{boardId}/")


}
