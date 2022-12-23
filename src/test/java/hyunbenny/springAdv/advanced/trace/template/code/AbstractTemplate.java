package hyunbenny.springAdv.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 메서드 패턴을 사용하면 중복도 줄어들고 수정할 때 한번만 수정하면 된다는 장점이 있다.
 * 하지만 SubLogic1, SubLogic2와 같이 클래스를 계속 만들어야 하는 단점이 있다.
 * 익명 내부 클래스를 사용하면 이 단점을 보완할 수 있다.
 */
@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        call();

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("result time : {}ms", resultTime);
    }

    public abstract void call();

}
