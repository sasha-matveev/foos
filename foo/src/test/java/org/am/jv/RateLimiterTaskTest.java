package org.am.jv;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class RateLimiterTaskTest {

    public static final String U_1 = "u1";
    public static final String U_2 = "u2";
    private final ControlClock clock = new ControlClock();

    @Test
    void shouldFailOnWrongArgs() {
        // todo : implement later to save time
    }

    @Test
    void shouldFilterWithinFloatingWindow() {
        int limit = 5;
        RateLimiterTask.RateLimiter rateLimiter = new RateLimiterTask.RateLimiter(clock, limit, Duration.ofSeconds(5), Duration.ofSeconds(30));
        for (int i = 0; i < limit - 1; i++) {
            assertThat(rateLimiter.allow(U_1)).isTrue();
        }
        clock.tick(Duration.ofSeconds(1));
        assertThat(rateLimiter.allow(U_1)).isTrue();
        assertThat(rateLimiter.allow(U_1)).isFalse();

        clock.tick(Duration.ofSeconds(4));
        assertThat(rateLimiter.allow(U_1)).isFalse();

        clock.tick(Duration.ofSeconds(1));
        for (int i = 0; i < limit - 1; i++) {
            assertThat(rateLimiter.allow(U_1)).isTrue();
        }
    }

    @Test
    void shouldFilterForTwoUsers() {
        int limit = 5;
        RateLimiterTask.RateLimiter rateLimiter = new RateLimiterTask.RateLimiter(clock, limit, Duration.ofSeconds(1), Duration.ofSeconds(30));

        for (int i = 0; i < limit; i++) {
            assertThat(rateLimiter.allow(U_1)).isTrue();
        }
        assertThat(rateLimiter.allow(U_1)).isFalse();

        assertThat(rateLimiter.allow(U_2)).isTrue();
    }

    private static class ControlClock extends Clock {

        private Instant instant = Instant.now();

        void tick(Duration duration) {
            this.instant = instant.plus(duration);
        }

        @Override
        public ZoneId getZone() {
            return null;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return null;
        }

        @Override
        public Instant instant() {
            return instant;
        }
    }
}