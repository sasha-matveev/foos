package org.am.i;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class YandexJavaSkillsTest {
    private YandexJavaSkills.CallbackScheduler scheduler;

    private static class CallbackResult {
        boolean isDone = false;
    }

    @BeforeEach
    public void setUp() {
        scheduler = new YandexJavaSkills.CallbackSchedulerImpl();
    }

    @AfterEach
    public void tearDown() throws Exception {
        scheduler.close();
    }

    @Test
    public void testSimple() throws InterruptedException {
        final CallbackResult result1 = new CallbackResult();
        final CallbackResult result2 = new CallbackResult();

        scheduler.schedule(
                () -> {
                    synchronized (result2) {
                        result2.isDone = true;
                        result2.notify();
                    }
                },
                Instant.now().plusSeconds(4)
        );
        scheduler.schedule(
                () -> {
                    synchronized (result1) {
                        result1.isDone = true;
                        result1.notify();
                    }
                },
                Instant.now().plusSeconds(2)
        );

        synchronized (result1) {
            result1.wait();
            assertTrue(result1.isDone);
            assertFalse(result2.isDone);
        }

        synchronized (result2) {
            result2.wait();
            assertTrue(result1.isDone);
            assertTrue(result2.isDone);
        }
    }
}