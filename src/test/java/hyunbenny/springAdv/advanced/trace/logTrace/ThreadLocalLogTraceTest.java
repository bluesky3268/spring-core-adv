package hyunbenny.springAdv.advanced.trace.logTrace;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.threadLocalLogTrace.ThreadLocalLogTrace;
import hyunbenny.springAdv.advanced.trace.threadlocal.code.FieldService;
import hyunbenny.springAdv.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalLogTraceTest {

    private ThreadLocalLogTrace logTrace = new ThreadLocalLogTrace();

    @Test
    void fieldLogTraceTest() {
        TraceStatus status1 = logTrace.begin("status1");
        TraceStatus status2 = logTrace.begin("status2");
        TraceStatus status3 = logTrace.begin("status3");
        logTrace.end(status3);
        logTrace.end(status2);
        logTrace.end(status1);
    }

    @Test
    void fieldLogTraceTest_ex() {
        TraceStatus status1 = logTrace.begin("status1");
        TraceStatus status2 = logTrace.begin("status2");
        TraceStatus status3 = logTrace.begin("status3");
        logTrace.end(status3);
        logTrace.exception(status2, new IllegalStateException());
        logTrace.end(status1);
    }
}
