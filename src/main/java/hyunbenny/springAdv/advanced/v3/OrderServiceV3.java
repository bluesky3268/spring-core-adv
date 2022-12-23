package hyunbenny.springAdv.advanced.v3;

import hyunbenny.springAdv.advanced.trace.TraceId;
import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV2.HelloTraceV2;
import hyunbenny.springAdv.advanced.trace.logTrace.FieldLogTrace;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace logTrace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderService - save()");
            orderRepositoryV3.save(status, itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
