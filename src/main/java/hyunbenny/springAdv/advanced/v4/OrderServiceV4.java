package hyunbenny.springAdv.advanced.v4;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepositoryV4;
    private final LogTrace logTrace;

    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                orderRepositoryV4.save(itemId);
                return null;
            }
        };
        template.execute("OrderServiceV4 - save()");
    }
}
