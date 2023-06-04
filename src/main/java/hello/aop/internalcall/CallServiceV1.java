package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /*
        spring boot 2.6 부터 setter 방식으로도 순환 참조가 안되도록 기본값 변경됨.
        해결하기 위해 property 설정 추가 spring.main.allow-circular-references=true
        https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes
         */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
