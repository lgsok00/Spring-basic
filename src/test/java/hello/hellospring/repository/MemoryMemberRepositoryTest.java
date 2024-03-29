package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 테스트는 순서에 상관없이 실행되어야 한다. (의존 X)
class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach // 테스트가 종료될 때 마다 실행 -> 메모리 DB에 저장된 데이터 삭제
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    // given
    Member member = new Member();
    member.setName("spring");

    // when
    repository.save(member);

    // then
    Member result = repository.findById(member.getId()).get(); // Optional 에서 값을 꺼낼 때 -> get()
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // when
    Member result = repository.findByName("spring1").get();

    // then
    assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findAll() {
    // given
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    // when
    List<Member> result = repository.findAll();

    // then
    assertThat(result.size()).isEqualTo(2);
  }
}
