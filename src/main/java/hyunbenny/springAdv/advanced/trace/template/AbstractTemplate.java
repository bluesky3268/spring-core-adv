package hyunbenny.springAdv.advanced.trace.template;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;

/**
 * 템플릿 메서드 패턴에서 부모 클래스 역할
 * 변하는 코드(핵심기능 - call())와 변하지 않는 코드(부가기능 - execute())의 구분
 * 템플릿 메서드 패턴의 목적
 *  : 작업에서 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 연기하기 위함
 *  -> 하위클래스가 알고리즘의 구조를 변경하지 않고도 특정 부분만 재정의할 수 있음
 *
 *  상속을 사용하기 때문에
 *  자식 클래스가 부모 클래스의 기능이 모두 필요가 없음에도 강하게 의존한다
 *  -> 부모 클래스와 자식 클래스의 관계가 강하게 결합된다는 단점이 있다.
 *  
 *  템플릿 메서드 패턴과 비슷한 역할을 하면서 상속의 단점을 제거할 수 있는 방법이 있다. 
 *  -> 전략 패턴
 */
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            T result = call();
            
            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }


    protected abstract T call();
}
