package com.project.callbus.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.callbus.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Optional<Member> findByAccountId(String name);
}
