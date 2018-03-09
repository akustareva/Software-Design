import aop.LoggingExecutionTimeAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Anna on 03.03.2018.
 */
public class LoggingExecutionTimeAspectTest {
    private LoggingExecutionTimeAspect loggingExecutionTimeAspect;
    @Mock
    private ProceedingJoinPoint joinPoint;
    @Mock
    private Signature signature;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        loggingExecutionTimeAspect = new LoggingExecutionTimeAspect();
    }

    @Test
    public void correctnessTest() throws Throwable {
        String methodName = "testMethod";
        String shortStr = "execution(CustomerManager.testMethod(..))";
        int NUM = 3;
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn(methodName);
        when(joinPoint.getArgs()).thenReturn(null);
        when(joinPoint.proceed(anyObject())).thenReturn(null);
        when(joinPoint.toShortString()).thenReturn(shortStr);
        for (int i = 0; i < NUM; i++) {
            loggingExecutionTimeAspect.logExecutionTime(joinPoint);
        }
        Assert.assertEquals(1, LoggingExecutionTimeAspect.getSum().size());
        Assert.assertEquals(1, LoggingExecutionTimeAspect.getCnt().size());
        Assert.assertTrue(LoggingExecutionTimeAspect.getCnt().get(shortStr) == NUM);
        Assert.assertNotNull(LoggingExecutionTimeAspect.getSum().get(shortStr));
    }
}
