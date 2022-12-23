package hyunbenny.springAdv.advanced.trace.strategy;

import hyunbenny.springAdv.advanced.trace.strategy.code.ContextV1;
import hyunbenny.springAdv.advanced.trace.strategy.code.StrategyLogic1;
import hyunbenny.springAdv.advanced.trace.strategy.code.StrategyLogic2;
import hyunbenny.springAdv.advanced.trace.strategy.code.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {


    @Test
    void ContextV1TestV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행

        log.info("로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}ms", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행

        log.info("로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}ms", resultTime);
    }

    @Test
    void strategyV1() {
        Strategy strategy1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();

        Strategy strategy2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }

    // 익명 내부 클래스를 사용하여 전략 패턴을 좀 더 편리하게 사용
    @Test
    void strategyV2() {
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }

    // V2 리팩토링
    @Test
    void strategyV3() {

        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        context2.execute();
    }


    // V3 리팩토링
    @Test
    void strategyV4() {
        // 아래와 같이 람다로 사용하려면 인터페이스에 메서드가 1개만 있어야 한다.
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        context2.execute();
    }

}
