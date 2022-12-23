package hyunbenny.springAdv.advanced.v1;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV1.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 helloTraceV1;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = helloTraceV1.begin("OrderRepository - save()");

            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생");
            }
            sleep(1000);

            helloTraceV1.end(status);

        } catch (Exception e) {
            helloTraceV1.exception(status, e);
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
