package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // @Controller 애노테이션이 붙으면 Spring 컨테이너가 스프링 객체를 생성
public class MemberController {
  private final MemberService memberService;

  @Autowired // Spring 컨테이너가 memberService를 찾아 자동으로 연결해준다.
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new") // data를 조회할 때
  public String createForm() {
    return "members/createMemberForm";
  }

  @PostMapping("/members/new") // data를 등록할 때
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}
