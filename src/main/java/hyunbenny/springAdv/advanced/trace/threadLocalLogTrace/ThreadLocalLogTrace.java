package hyunbenny.springAdv.advanced.trace.threadLocalLogTrace;

import hyunbenny.springAdv.advanced.trace.TraceId;
import hyunbenny.springAdv.advanced.trace.TraceStatus;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EXCEPTION_PREFIX = "<-X-";

    /** 파라미터로 직접 넘겨주지 않으면 TraceId를 어디선가 가지고 있어야 하는데
     * traceIdHolder에 보관해놓고 동기화 하여 사용
     * # 싱글톤에 등록된 스프링 빈이기 때문에 동시성 이슈가 발생함!!!
     * # 지역변수는 메모리가 각각 할당이 되서 동시성 이슈가 발생하지 않는데 
     * # 싱글톤으로 사용되거나 static으로 할당된 공용 필드의 경우 값이 변하게 되면 동시성 이슈가 문제가 된다(값을 읽기만 할 때는 문제가 되지 않음)
     * -> ThreadLocal을 사용해서 해결해보자!!
     * ThreadLocal : 해당 쓰레드만 접근할 수 있는 특별한 저장소.
     * 저장 : set(), 조회 : get(), 다쓰면 remove()
     * ThreadLocal 사용시 주의점 : 메모리 누수 방지를 위해 ThreadLocal을 다 사용하고 나면  리소스를 항상 릴리즈 해줘야 한다.(ThreadLocal.remove())
     */
    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = this.traceIdHolder.get();
        long startTimeMillis = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMillis, message);
    }

    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void syncTraceId() {
        TraceId traceId = this.traceIdHolder.get();
        if (traceId == null) {
            this.traceIdHolder.set(new TraceId());
        }else{
            this.traceIdHolder.set(traceId.createNextTraceId());
        }
    }

    private void complete(TraceStatus traceStatus, Exception e) {

        Long stopTimeMillis = System.currentTimeMillis();
        long spentTime = stopTimeMillis - traceStatus.getStartTimeMs();

        TraceId traceId = traceStatus.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), traceStatus.getMessage(), spentTime);
        }else{
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EXCEPTION_PREFIX, traceId.getLevel()), traceStatus.getMessage(), spentTime, e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        // 제일 처음 레벨로 돌아오면 리소스를 릴리즈 해줘야 한다.
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove();
        }else{
            traceIdHolder.set(traceId.createPreviousTraceId());
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|    ");
        }
        return sb.toString();
    }

}
