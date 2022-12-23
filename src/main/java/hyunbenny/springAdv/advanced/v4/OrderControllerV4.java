package hyunbenny.springAdv.advanced.v4;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  로그 추적으로 위해 TraceStatus를 사용 + 동기화를 위해 파라미터로 traceId를 넘겨줌
 *  -> 레벨 표시는 되지만 여전히 일일이 TraceStatus를 직접 코드를 넣어줘야 하고
 *  traceStatusSync를 추가한 것과 같이 수정이 되는 경우 사용하는 모든 코드 심지어 인터페이스까지 수정을 해야하는
 *  경우가 발생할 수 있다.
 *  귀찮고 비효율적이다.
 *
 */

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderServiceV4;
    private final LogTrace logTrace;

    @GetMapping("/v4/save")
    public String save(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate(logTrace) {
            @Override
            protected Object call() {
                orderServiceV4.orderItem(itemId);
                return "OK";
            }
        };
        return template.execute("OrderControllerV4 - save()");
    }
}
