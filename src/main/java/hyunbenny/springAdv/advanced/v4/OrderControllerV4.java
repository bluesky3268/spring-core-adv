package hyunbenny.springAdv.advanced.v4;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
