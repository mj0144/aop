package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Slf4j
@Aspect //컴포넌트 스캔에 잡히지는 않음.
public class AspectV3 {

    // hello.aop.order 패키지와 하위 패키지
    // 다른 aspect에서 참고하려면 public 으로 지정해야함.
    // 이런식으로 분리시, 하나의 포인트커 표현식을 여러 advice에서 사용가능.
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} // pointcut 시그니처. == 메서드이름과 파라미터 합친 것. 반환값은 void여야만 함.

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){}

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
        return joinPoint.proceed();
    }

    //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    // [클라이언트] -- doLog -- doTransaction --[OrderService] -- doLog -- [OrderRepository]
    @Around("allOrder() && allService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());

            return  result;

        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;

        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }

    }

}
