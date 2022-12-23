package hyunbenny.springAdv.advanced.trace.helloTraceV1;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV2.HelloTraceV2;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void trace_begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @Test
    void trace_begin_ex() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalArgumentException());
    }

    @Test
    void traceSync() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("status1");
        TraceStatus status2 = trace.traceStatusSync(status.getTraceId(), "status2");
        TraceStatus status3 = trace.traceStatusSync(status2.getTraceId(), "status3");
        trace.end(status3);
        trace.end(status2);
        trace.end(status);
    }

    @Test
    void traceSync_ex() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status = trace.begin("status1");
        TraceStatus status2 = trace.traceStatusSync(status.getTraceId(), "status2");
        TraceStatus status3 = trace.traceStatusSync(status2.getTraceId(), "status3");
        trace.end(status3);
        trace.exception(status2, new IllegalArgumentException());
        trace.end(status);
    }

}