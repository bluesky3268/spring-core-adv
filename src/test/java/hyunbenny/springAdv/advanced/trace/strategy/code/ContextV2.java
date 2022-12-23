package hyunbenny.springAdv.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 *  V2 : 전략을 파라미터로 받는 방식
 *  V1과 같이 선조립 후실행 방식이 아니기 때문에 실행할 때마다 전략을 유연하게 변경할 수 있다는 장점이 있음
 *  하지만 단점 또한 실행할 때마다 전략을 같이 넘겨줘야한다는 점
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        strategy.call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime : {}", resultTime);

    }
}
