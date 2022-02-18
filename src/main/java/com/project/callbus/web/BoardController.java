package com.project.callbus.web;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.service.BoardService;
import com.project.callbus.web.dto.BoardAssembler;
import com.project.callbus.web.dto.BoardResource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;
	private final BoardAssembler boardAssembler;

	//글 목록 조회
	@GetMapping("/api/v1/board")
	public ResponseEntity<?> boardList(@PageableDefault Pageable pageable/*, @AuthenticationPrincipal CustomUserDetail customUserDetail*/){
		Page<Board> postList = boardService.getBoardList(pageable);
		CollectionModel<BoardResource> collectionModel = boardAssembler.toCollectionModel(postList);
		PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(pageable.getPageSize(),postList.getNumber(),postList.getTotalElements());
		PagedModel<BoardResource> body = PagedModel.of(collectionModel.getContent(),pageMetadata);

		body.add(linkTo(methodOn(BoardController.class).boardList(pageable)).withSelfRel());
		return ResponseEntity.ok(body);
	}

	// //글 조회
	// @GetMapping("/api/v1/board/{boardId}")
	// public ResponseEntity<BoardResource> board(@PathVariable Long boardId) {
	// 	BoardResource boardResource = boardService.findBoardById(boardId);
	// 	return ResponseEntity.ok(boardResource);
	// }
	//
	// //글 쓰기
	// @PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	// @PostMapping("/api/v1/board")
	// public void crateBoard(@RequestBody RequestBoardDto requestBoardDto, @AuthenticationPrincipal CustomUserDetail customUserDetail) {
	// 	boardService.writeBoard(customUserDetail.getUsername(), requestBoardDto);
	// }
	//
	// //글 수정
	// @PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	// @PutMapping("/api/v1/board/{boardId}")
	// public void updateBoard(@PathVariable Long boardId, @RequestBody BoardResource boardResource) {
	// 	boardService.updateBoard(boardId, boardResource);
	// }
	//
	// //글 삭제
	// @PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	// @DeleteMapping("/api/v1/board/{boardId}")
	// public void deleteBoard(@PathVariable Long boardId) {
	// 	boardService.deleteBoard(boardId);
	// }
	//
	// //좋아요 누르기
	// @PreAuthorize("!hasAnyAuthority('ROLE_ANONYMOUS')")
	// @PostMapping("/api/v1/like/{boardId}/") //왜 헤더에 다른 아이디 넣었는데 인식을 못하지..
	// public void likeBoard(@PathVariable Long boardId, @AuthenticationPrincipal CustomUserDetail customUserDetail){
	// 	boardService.likedBoard(boardId, customUserDetail.getUsername());
	// }
}
