package hyunbenny.springAdv.advanced.v4;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 템플릿 메서드 패턴을 사용하여
 * 변하는 코드(핵심기능 - call())와 변하지 않는 코드(부가기능 - execute())의 구분
 * 보다 편리하게 사용 
 */
@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderServiceV4;
    private final LogTrace logTrace;

    @GetMapping("/v4/save")
    public String save(String itemId) {

        // AbstractTemplate를 상속받는 클래스를 따로 만들어도 되지만 편리하게 익명 클래스를 사용
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
