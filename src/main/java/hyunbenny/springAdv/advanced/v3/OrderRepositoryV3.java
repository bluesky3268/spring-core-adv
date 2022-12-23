package hyunbenny.springAdv.advanced.v3;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV2.HelloTraceV2;
import hyunbenny.springAdv.advanced.trace.logTrace.FieldLogTrace;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace logTrace;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepositoryV3 - save()");

            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생");
            }
            sleep(1000);

            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int sleepMillisec) {
        try{
            Thread.sleep(sleepMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
