package com.project.callbus.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.project.callbus.domain.board.entity.Board;
import com.project.callbus.web.BoardController;

@Component
public class BoardAssembler extends RepresentationModelAssemblerSupport<Board, BoardResource> {

	public BoardAssembler() {
		super(BoardController.class, BoardResource.class);
	}

	/**
	 * entity를 resource 객체로 변환
	 */
	@Override
	protected BoardResource instantiateModel(Board entity) {
		return new BoardResource(entity);
	}

	/**
	 * entity의 id로 링크를 만들고 resource 객체로 반환
	 */
	@Override
	public BoardResource toModel(Board entity) {
		return createModelWithId(entity.getId(), entity);
	}

	/**
	 * entity 여러개를 한번에 변환하여 CollectionModel로 반환
	 * @param entities
	 * @return
	 */
	@Override
	public CollectionModel<BoardResource> toCollectionModel(Iterable<? extends Board> entities) {
		List<BoardResource> boardList = new ArrayList<>();
		for (Board entity : entities) {
			boardList.add(toModel(entity));
		}
		return CollectionModel.of(boardList);
	}


}
