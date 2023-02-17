package hello.aop.member.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 앱 실행시까지 어노테이션 살아있음
public @interface MethodAop {
    String value();
}
