package hyunbenny.springAdv.advanced.v1;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV1.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 helloTraceV1;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = helloTraceV1.begin("OrderService - save()");
            orderRepositoryV1.save(itemId);
            helloTraceV1.end(status);

        } catch (Exception e) {
            helloTraceV1.exception(status, e);
            throw e;
        }
    }
}
