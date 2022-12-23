package hyunbenny.springAdv.advanced.trace.threadlocal;

import hyunbenny.springAdv.advanced.trace.threadlocal.code.FieldService;
import hyunbenny.springAdv.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    @DisplayName("동시성 문제가 발생안하는 경우")
    void field() {
        log.info("===== main start =====");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000);
        threadB.start();
        sleep(2000); // countDown latch를 사용.. ?
    }

    @Test
    @DisplayName("동시성 문제가 발생")
    void field_concurrency() {
        log.info("===== main start =====");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();
        sleep(2000); // countDown latch를 사용.. ?
    }

    @Test
    @DisplayName("ThreadLocal로 동시성 문제 해결")
    void field_threadLocal() {
        log.info("===== main start =====");

        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };

        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();
        sleep(2000); // countDown latch를 사용.. ?
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
