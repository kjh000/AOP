package hello.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV4Pointcut {


    @Around("hello.aop.Pointcuts.allOrder()") // pointcut, 이 경로에 있는 것들은 프록시 처리가 되면서 aop 적용됨
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[log] {}",joinPoint.getSignature()); // join point 시그니쳐
        return joinPoint.proceed();
    }



    //hello.aop.order 패키지와 하위패키지 이면서 클래스 이름이 *Service
    @Around("hello.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;

        }catch (Exception e){
            log.info("[트랜잭션 롤백] {}",joinPoint.getSignature());
            throw e;
        }finally {
            log.info("[리소스 릴리즈] {}",joinPoint.getSignature());
        }
    }
}
