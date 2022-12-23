package hyunbenny.springAdv.advanced;

import hyunbenny.springAdv.advanced.trace.logTrace.FieldLogTrace;
import hyunbenny.springAdv.advanced.trace.logTrace.LogTrace;
import hyunbenny.springAdv.advanced.trace.threadLocalLogTrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace(); // V3 쓰레드 로컬 적용 전 동시성 이슈 발생
        return new ThreadLocalLogTrace(); // V3 쓰레드 로컬을 사용하여 동시성 이슈 해결
    }
}
