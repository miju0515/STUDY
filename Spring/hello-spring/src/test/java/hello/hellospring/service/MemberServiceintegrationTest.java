package hello.hellospring.service;

/*
    ctrl+shift+T -> 테스트 케이스 만들기
    shift+f6 -> rename
    테스트 코드에서는 한글로 적어도 됨
    ctrl+shift+enter -> 세미콜론 붙여줌
    shift+f10 -> 이전 행동 반복
 */

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceintegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void 회원가입() {
        //given 주어지는 것
        Member member =new Member();
        member.setName("hello");
        //when 언제
        Long saveId = memberService.join(member);
        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /*try{
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }*/
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}