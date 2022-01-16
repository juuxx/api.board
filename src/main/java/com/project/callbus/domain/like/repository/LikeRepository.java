package com.project.callbus.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.callbus.domain.like.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
