package hyunbenny.springAdv.advanced.trace;

import lombok.Getter;

/**
 * 로그를 시작할 때의 상태 정보를 가지고 로그를 종료할 때 사용됨
 */
@Getter
public class TraceStatus {

    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}
