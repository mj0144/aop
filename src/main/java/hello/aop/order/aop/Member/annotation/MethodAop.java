package hello.aop.order.aop.Member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 실행될때까지 애노테이션이 살아있음.
public @interface MethodAop {
    String value();
}
