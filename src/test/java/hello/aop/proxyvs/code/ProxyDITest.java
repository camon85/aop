package hello.aop.proxyvs.code;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) // JDK 동적 프록시
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) // CGLIB 프록시
@SpringBootTest // Spring 2.0 부터는 cglib가 기본 사용
@Import(ProxyDIAspect.class)
public class ProxyDITest {

    @Autowired
    MemberService memberService;

    // JDK 동적 프록시에서는 구체 클래스로 주입 받을 수 없다. (MemberService 인터페이스 기반으로 만들어지기 때문)
    @Autowired
    MemberServiceImpl memberServiceImpl;

    @Test
    void go() {
        log.info("memberService class={}", memberService.getClass());
        log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
        memberServiceImpl.hello("hello");
    }
}
