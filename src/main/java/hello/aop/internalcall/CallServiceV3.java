package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 구조를 분리 변경
 */
@Slf4j
@Component
public class CallServiceV3 {

    @Autowired
    private InternalService internalService;

    public void external(){
      log.info("call external");
        internalService.internal(); // 주입받은 객체(프록시)에서 호출.
    }




}
