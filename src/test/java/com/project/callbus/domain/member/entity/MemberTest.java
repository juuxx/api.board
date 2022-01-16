package com.project.callbus.domain.member.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.project.callbus.domain.enums.AccountType;
import com.project.callbus.domain.enums.Quit;
import com.project.callbus.domain.member.repository.MemberRepository;

@DataJpaTest
class MemberTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Commit
	void name() {
		//given
		Member member = Member.builder().accountId("user124")
										.accountType(AccountType.LESSEE)
										.nickname("서명주")
										.quit(Quit.NO).build();
		//when
		memberRepository.save(member);
		Member member1 = memberRepository.findByAccountId("user124")
			.orElseThrow(() -> new IllegalArgumentException("틀림"));
		//then
		assertThat(member).isEqualTo(member1);
	}
}