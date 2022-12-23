package hyunbenny.springAdv.advanced.v2;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV2.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 helloTraceV2;

    public void save(TraceStatus traceStatus, String itemId) {
        TraceStatus status = null;
        try {
            status = helloTraceV2.traceStatusSync(traceStatus.getTraceId(), "OrderRepository - save()");

            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생");
            }
            sleep(1000);

            helloTraceV2.end(status);

        } catch (Exception e) {
            helloTraceV2.exception(status, e);
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
