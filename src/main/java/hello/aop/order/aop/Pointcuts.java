package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* hello.aop.order..*(..))")
    public void allOrder(){} // pointcut 시그니처. == 메서드이름과 파라미터 합친 것. 반환값은 void여야만 함.

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}


    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    public void orderAndService(){

    }
}
