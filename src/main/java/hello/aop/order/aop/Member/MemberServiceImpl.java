package hello.aop.order.aop.Member;

import hello.aop.order.aop.Member.annotation.ClassAop;
import hello.aop.order.aop.Member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }
}
