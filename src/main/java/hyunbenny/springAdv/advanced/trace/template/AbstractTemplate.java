package hyunbenny.springAdv.advanced.trace.template;

import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;

/**
 * 템플릿 메서드 패턴에서 부모 클래스 역할
 * 변하는 코드(핵심기능 - call())와 변하지 않는 코드(부가기능 - execute())의 구분
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
