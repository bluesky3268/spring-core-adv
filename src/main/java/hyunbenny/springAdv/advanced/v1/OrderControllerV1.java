package hyunbenny.springAdv.advanced.v1;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.helloTraceV1.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그 추적으로 위해 TraceStatus를 사용
 * -> Controller, Service, Repository 마다 일일이 붙여줘야 하고
 * -> 레벨 표시가 안됨.
 */

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 orderServiceV1;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/save")
    public String save(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderControllerV1 -save()");
            orderServiceV1.orderItem(itemId);
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }

        return itemId + " : save 성공";
    }
}
