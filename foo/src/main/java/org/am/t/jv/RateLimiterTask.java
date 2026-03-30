package org.am.t.jv;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTask {
    interface IRateLimiter {
        boolean allow(String userId);
    }

    public static class RateLimiter implements IRateLimiter {
        private final Map<String, UserLimits> users = new ConcurrentHashMap<>();

        private final Clock clock;
        private final int limit;
        private final Duration window;
        private final Duration cleanupInterval;

        public RateLimiter(Clock clock, int limit, Duration window, Duration cleanupInterval) {
            this.clock = Objects.requireNonNull(clock);
            this.limit = limit; // google findbugs has Preconditions.checkArgument (limit > 0)
            this.window = Objects.requireNonNull(window);
            this.cleanupInterval = cleanupInterval;
            Executors.newSingleThreadScheduledExecutor() // need shutdown() on application shutdown
                    .scheduleAtFixedRate(
                            () -> cleanup(),
                            cleanupInterval.toMillis(),
                            cleanupInterval.toMillis(),
                            TimeUnit.MILLISECONDS
                    );
        }

        @Override
        public boolean allow(String userId) {
            return users.computeIfAbsent(userId, k -> new UserLimits(clock, limit, window)).allow();
        }

        private void cleanup() {
            for (String userId : users.keySet()) {
                users.compute(userId, (k, u) -> {
                    if (u == null) {
                        return null;
                    }
                    if (u.cleanup() == 0) {
                        return null;
                    } else {
                        return u;
                    }
                });
            }
        }
    }

    private static class UserLimits {
        private final Deque<Long> timestamps = new ArrayDeque<>();
        private final Clock clock;
        private final int limit;
        private final Duration window;

        public UserLimits(Clock clock, int limit, Duration window) {
            this.clock = clock;
            this.limit = limit;
            this.window = window;
        }

        private boolean allow() {
            synchronized (timestamps) {
                long now = clock.millis();
                if (!timestamps.isEmpty() && now - window.toMillis() > timestamps.getFirst()) {
                    timestamps.removeFirst();
                }
                if (timestamps.size() >= limit) {
                    return false;
                }
                timestamps.add(now);
                return true;
            }
        }

        private int cleanup() {
            synchronized (timestamps) {
                long now = clock.millis();
                timestamps.removeIf(aLong -> aLong < now - window.toMillis());
                return timestamps.size();
            }
        }
    }

}
