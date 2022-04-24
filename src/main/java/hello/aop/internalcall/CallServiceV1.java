package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 예외 터짐. 아직 자기자신이 만들어져 있지 않았는데, 주입 받아서.
     */
//    @Autowired
//    public CallServiceV1(CallServiceV1 callServiceV1) {
//        this.callServiceV1 = callServiceV1;
//    }


    /**
     * 이것도 순환 예외 터짐.
     * @param callServiceV1
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 setter={}", callServiceV1.getClass());
        this.callServiceV1 = callServiceV1;
    }



    public void external(){
      log.info("call external");
      callServiceV1.internal(); // 주입받은 객체(프록시)에서 호출.
    }

    public void internal(){
        log.info("call internal");
    }


}
