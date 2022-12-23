package hyunbenny.springAdv.advanced.trace.logTrace;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace logTrace = new FieldLogTrace();

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