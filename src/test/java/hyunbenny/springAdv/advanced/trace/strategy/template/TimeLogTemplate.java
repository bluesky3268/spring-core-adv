package hyunbenny.springAdv.advanced.trace.strategy.template;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        log.info("startTime : {}", startTime);

        callback.call();

        long endTime = System.currentTimeMillis();
        log.info("end time : {}", endTime);

        long resultTime = endTime - startTime;
        log.info("resultTime : {}ms", resultTime);
    }

}
