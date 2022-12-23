package hyunbenny.springAdv.advanced.v5;

import hyunbenny.springAdv.advanced.trace.callback.TraceTemplate;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.template = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        template.execute("OrderRepositoryV5 - save()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int sleepMillisec) {
        try{
            Thread.sleep(sleepMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
