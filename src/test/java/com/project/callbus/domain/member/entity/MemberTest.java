package com.project.callbus.domain.member.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import com.project.callbus.domain.enums.AccountType;
import com.project.callbus.domain.enums.Quit;
import com.project.callbus.domain.member.repository.MemberRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MemberTest {

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@Commit
	void name() {
		//given
		Member member = Member.builder().accountId("user1234")
										.accountType(AccountType.LESSEE)
										.nickname("테스트")
										.quit(Quit.NO).build();
		//when
		memberRepository.save(member);
		Member member1 = memberRepository.findByAccountId("user1234")
			.orElseThrow(() -> new IllegalArgumentException("틀림"));
		//then
		assertThat(member).isEqualTo(member1);
	}
}