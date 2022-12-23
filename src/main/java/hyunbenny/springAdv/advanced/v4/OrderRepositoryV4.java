package hyunbenny.springAdv.advanced.v4;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace logTrace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(logTrace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalArgumentException("예외 발생");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepositoryV4 - save()");
    }

    private void sleep(int sleepMillisec) {
        try{
            Thread.sleep(sleepMillisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
