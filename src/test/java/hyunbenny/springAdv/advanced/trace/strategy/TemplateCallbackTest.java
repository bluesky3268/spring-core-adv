package hyunbenny.springAdv.advanced.trace.strategy;

import hyunbenny.springAdv.advanced.trace.strategy.template.Callback;
import hyunbenny.springAdv.advanced.trace.strategy.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1() {
        TimeLogTemplate timeLog1 = new TimeLogTemplate();
        timeLog1.execute(new Callback() {
            @Override
            public void call() {
                log.info("템플릿 콜백 패턴 - 익명 내부 클래스");
            }
        });

        TimeLogTemplate timeLog2 = new TimeLogTemplate();
        timeLog2.execute(new Callback() {
            @Override
            public void call() {
                log.info("템플릿 콜백 패턴2 - 익명 내부 클래스");
            }
        });
    }

    /**
     * 템플릿 콜백 패턴 - 람다
     */
    @Test
    void callbackV2() {
        TimeLogTemplate timeLog1 = new TimeLogTemplate();
        timeLog1.execute(() -> log.info("템플릿 콜백 패턴 - 익명 내부 클래스"));

        TimeLogTemplate timeLog2 = new TimeLogTemplate();
        timeLog2.execute(() -> log.info("템플릿 콜백 패턴2 - 익명 내부 클래스"));
    }

}
