package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    @Test
    void external() {
        /*
        external 내부에서 호출하는 this.internal()은 proxy가 아니라 실제 대상 객체의 인스턴스를 뜻하므로,
        internal은 aop가 동작하지 않는다.

        프록시 방식의 한계: 내부 호출에 aop 적용 할 수 없다.
         */
        callServiceV0.external();
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}