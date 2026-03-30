package org.am.t.jv;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Transactions {
    public static class Transaction {
        private final String accountId;
        private final BigDecimal amount;
        private final LocalDateTime timestamp;

        public Transaction(String accountId, BigDecimal amount, LocalDateTime timestamp) {
            this.accountId = accountId;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        public boolean isValid() {
            return this.accountId != null && this.amount != null && this.timestamp != null;
        }

        /**
         * both "ends" are inclusive.
         */
        public boolean happenedWithin(LocalDateTime lower, LocalDateTime upper) {
            return !this.timestamp.isAfter(upper) && !this.timestamp.isBefore(lower);
        }


        public static Collector<Transaction, ?, Map<String, BigDecimal>> collectToMapWithAmountSum() {
            return Collectors.toMap(
                    t -> t.accountId,
                    t -> t.amount,
                    (bd1, bd2) -> bd1.add(bd2)
            );
        }
    }

    public interface IClock {
        LocalDateTime now();
    }

    public static class SystemClock implements IClock {
        @Override
        public LocalDateTime now() {
            return LocalDateTime.now();
        }
    }

    /**
     * For testing purposes.
     */
    public static class FixedClock implements IClock {
        private final LocalDateTime fixed;

        public FixedClock(LocalDateTime fixed) {
            this.fixed = fixed;
        }

        @Override
        public LocalDateTime now() {
            return fixed;
        }
    }

    public static Map<String, BigDecimal> getTotalAmountPerAccountLast24h(IClock clock, List<Transaction> transactions) {
        Objects.requireNonNull(clock);
        Objects.requireNonNull(transactions);
        LocalDateTime now = clock.now();
        LocalDateTime cutoff = now.minus(Duration.ofHours(24));
        return transactions.stream()
                .filter(t -> t != null && t.isValid())
                .filter(t -> t.happenedWithin(cutoff, now))
                .collect(Transaction.collectToMapWithAmountSum());

    }
}
