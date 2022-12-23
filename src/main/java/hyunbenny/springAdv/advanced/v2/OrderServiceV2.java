package hyunbenny.springAdv.advanced.v2;

import hyunbenny.springAdv.advanced.trace.TraceId;
import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV2.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 helloTraceV2;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = helloTraceV2.traceStatusSync(traceId, "OrderService - save()");
            orderRepositoryV2.save(status, itemId);
            helloTraceV2.end(status);

        } catch (Exception e) {
            helloTraceV2.exception(status, e);
            throw e;
        }
    }
}
