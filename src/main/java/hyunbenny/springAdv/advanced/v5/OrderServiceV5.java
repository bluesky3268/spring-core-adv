package hyunbenny.springAdv.advanced.v5;

import hyunbenny.springAdv.advanced.trace.callback.TraceTemplate;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepositoryV5;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryV5, LogTrace logTrace) {
        this.orderRepositoryV5 = orderRepositoryV5;
        this.template = new TraceTemplate(logTrace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderServiceV5 - save()", () -> {
            orderRepositoryV5.save(itemId);
            return null;
        });
    }

}
