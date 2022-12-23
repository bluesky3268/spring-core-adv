package hyunbenny.springAdv.advanced.v3;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV1.HelloTraceV1;
import hyunbenny.springAdv.advanced.trace.logTrace.FieldLogTrace;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LogTrace를 사용하여 직접 일일이 코드를 작성하거나 비지니스 로직에 불필요한 파라미터를 같이 넘기지 않아도 됨
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderServiceV3;
    private final LogTrace logTrace;

    @GetMapping("/v3/save")
    public String save(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = logTrace.begin("OrderControllerV3 -save()");
            orderServiceV3.orderItem(itemId);
            logTrace.end(traceStatus);
        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }

        return itemId + " : save 성공";
    }
}
