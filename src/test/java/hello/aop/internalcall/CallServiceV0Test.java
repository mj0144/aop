package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Import(CallLogAspect.class)
@SpringBootTest
@Slf4j
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;

    /**
     * external 내부에서 internal()을 호출하고 있지만 여기에는 aop걸리지 않음.
     * internal()이 대상이긴 하나, 자기자신의 내부 메서드의 호출은 프록시를 거치지 않기 때문에 어드바이스가 적용인 안된것.
     */
    @Test
    void external() {
        //log.info("target={}",callServiceV0.getClass());
        callServiceV0.external();
    }

    /**
     * 여기서는 정상적으로 aop 적용됨.
     */
    @Test
    void internal() {
        callServiceV0.internal();
    }
}