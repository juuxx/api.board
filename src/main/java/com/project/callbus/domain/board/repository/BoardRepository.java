package com.project.callbus.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.callbus.domain.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
