package hyunbenny.springAdv.advanced.trace.helloTraceV2;

import hyunbenny.springAdv.advanced.trace.TraceId;
import hyunbenny.springAdv.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<-X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMillis, message);
    }

    public TraceStatus traceStatusSync(TraceId traceId, String message) {
        TraceId nextTraceId = traceId.createNextTraceId();
        long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", nextTraceId.getId(), addSpace(START_PREFIX, nextTraceId.getLevel()), message);
        return new TraceStatus(nextTraceId, startTimeMillis, message);
    }

    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void complete(TraceStatus traceStatus, Exception e) {

        Long stopTimeMillis = System.currentTimeMillis();
        long spentTime = stopTimeMillis - traceStatus.getStartTimeMs();

        TraceId traceId = traceStatus.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), traceStatus.getMessage(), spentTime);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), traceStatus.getMessage(), spentTime, e.toString());
        }

    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|    ");
        }
        return sb.toString();
    }



}
