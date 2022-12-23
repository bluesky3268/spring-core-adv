package hyunbenny.springAdv.advanced.trace.strategy;

import hyunbenny.springAdv.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행 - 익명 클래스");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행 - 익명 클래스");
            }
        });
    }

    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();

        context.execute(() -> log.info("비지니스 로직1 실행 - 익명 클래스 + 람다"));
        context.execute(() -> log.info("비지니스 로직2 실행 - 익명 클래스 + 람다"));
    }

}
