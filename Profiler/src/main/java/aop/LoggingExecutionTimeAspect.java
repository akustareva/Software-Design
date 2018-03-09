package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anna on 03.03.2018.
 */
@Aspect
public class LoggingExecutionTimeAspect {
    private static Map<String, Long> sum = new HashMap<>();
    private static Map<String, Long> cnt = new HashMap<>();

    @Around("execution(* domain.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNs = System.nanoTime();
        System.out.println("Start method " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(joinPoint.getArgs());
        long time = (System.nanoTime() - startNs);
        System.out.println("Finish method " + joinPoint.getSignature().getName() + ", execution time in ns: " + time);
//        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
//            System.out.println(e);
//        }
        sum.put(joinPoint.toShortString(), sum.getOrDefault(joinPoint.toShortString(), 0L) + time);
        cnt.put(joinPoint.toShortString(), cnt.getOrDefault(joinPoint.toShortString(), 0L) + 1L);
        return result;
    }

    public static Map<String, Long> getSum() {
        return sum;
    }

    public static Map<String, Long> getCnt() {
        return cnt;
    }
}
