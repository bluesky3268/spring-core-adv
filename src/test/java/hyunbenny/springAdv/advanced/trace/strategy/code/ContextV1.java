package hyunbenny.springAdv.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴
 * Context : 변하지 않는 로직을 가지고 있는 템플릿의 역할로 Strategy인터페이스에 의존하기 때문에
 * 구현체의 코드가 변경되어도 Context의 코드는 변경하지 않아도 된다.
 *
 * 스프링에서 의존관계를 주입할 때 사용하는 방식이 바로 이 전략 패턴이다.
 */
// V1 : 필드에 전략을 보관하는 방식
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime : {}", resultTime);

    }
}
