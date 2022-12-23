package hyunbenny.springAdv.advanced.v5;

import hyunbenny.springAdv.advanced.trace.callback.TraceTemplate;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderServiceV5;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderServiceV5 = orderService;
        this.template = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/save")
    public String save(String itemId) {
        return template.execute("OrderControllerV5 - save", () -> {
            orderServiceV5.orderItem(itemId);
            return "OK";
        });
    }

}
