package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;


/**
 * @Order는 Aspect 단위로 순서가 매겨짐.
 * 즉, 클래스 단위로 순서가 매겨짐.
 */


@Slf4j
//@Aspect //컴포넌트 스캔에 잡히지는 않음.
public class AspectV5Order {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처
            return joinPoint.proceed();
        }

    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        //hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
        // [클라이언트] -- doLog -- doTransaction --[OrderService] -- doLog -- [OrderRepository]
        @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try{
                //@Before
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

                Object result = joinPoint.proceed();

                //@AfterReturning
                log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());

                return  result;

            } catch (Exception e) {
                //@AfterThrowing
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;

            } finally {
                //@After
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }

        }

    }
}
