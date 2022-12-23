package hyunbenny.springAdv.advanced.trace.logTrace;

import hyunbenny.springAdv.advanced.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);

    void end(TraceStatus traceStatus);

    void exception(TraceStatus traceStatus, Exception e);
}
