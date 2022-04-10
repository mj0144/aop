package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Slf4j
@Aspect //컴포넌트 스캔에 잡히지는 않음.
public class AspectV2 {

    // hello.aop.order 패키지와 하위 패키지
    // 다른 aspect에서 참고하려면 public 으로 지정해야함.
    // 이런식으로 분리시, 하나의 포인트커 표현식을 여러 advice에서 사용가능.
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} // pointcut 시그니처. == 메서드이름과 파라미터 합친 것. 반환값은 void여야만 함.

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    @Around("allOrder()")
    public Object doLog2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }
}
