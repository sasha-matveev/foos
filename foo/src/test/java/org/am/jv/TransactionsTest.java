package org.am.jv;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TransactionsTest {

    @Test
    void shouldThrowOnNulls() {
        Assertions.assertThatThrownBy(() -> Transactions.getTotalAmountPerAccountLast24h(
                null,
                new ArrayList<>()
        )).isInstanceOf(NullPointerException.class);
        Assertions.assertThatThrownBy(() -> Transactions.getTotalAmountPerAccountLast24h(
                new Transactions.SystemClock(),
                null
        )).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldFilterNulls() {
        Transactions.FixedClock clock = new Transactions.FixedClock(LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 10)
        ));

        LocalDateTime t1 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 00)
        );

        Map<String, BigDecimal> result = Transactions.getTotalAmountPerAccountLast24h(
                clock,
                List.of(
                        new Transactions.Transaction(null, BigDecimal.valueOf(10), t1),
                        new Transactions.Transaction("a2", null, t1),
                        new Transactions.Transaction("a3", BigDecimal.valueOf(30), null),
                        new Transactions.Transaction("a4", BigDecimal.valueOf(40), t1)
                )
        );
        Assertions.assertThat(result)
                .containsEntry("a4", BigDecimal.valueOf(40))
                .hasSize(1);
    }

    @Test
    void shouldCollectAndSum() {
        Transactions.FixedClock clock = new Transactions.FixedClock(LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 10)
        ));

        LocalDateTime t1 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 00)
        );
        LocalDateTime t2 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 00)
        );
        LocalDateTime t3 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 00)
        );

        Map<String, BigDecimal> result = Transactions.getTotalAmountPerAccountLast24h(
                clock,
                List.of(
                        new Transactions.Transaction("a1", BigDecimal.valueOf(10), t1),
                        new Transactions.Transaction("a1", BigDecimal.valueOf(15), t2),
                        new Transactions.Transaction("a2", BigDecimal.valueOf(20), t3)
                )
        );
        Assertions.assertThat(result)
                .containsEntry("a1", BigDecimal.valueOf(25))
                .containsEntry("a2", BigDecimal.valueOf(20))
                .hasSize(2);
    }

    @Test
    void shouldFilterByDate() {
        Transactions.FixedClock clock = new Transactions.FixedClock(LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 10)
        ));

        LocalDateTime t1 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 30)
        );
        LocalDateTime t2 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(23, 10)
        );
        LocalDateTime t3 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 28),
                LocalTime.of(10, 00)
        );
        LocalDateTime t4 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 27),
                LocalTime.of(23, 10)
        );
        LocalDateTime t5 = LocalDateTime.of(
                LocalDate.of(2026, Month.MARCH, 27),
                LocalTime.of(10, 00)
        );

        Map<String, BigDecimal> result = Transactions.getTotalAmountPerAccountLast24h(
                clock,
                List.of(
                        new Transactions.Transaction("a1", BigDecimal.valueOf(11), t1),
                        new Transactions.Transaction("a2", BigDecimal.valueOf(12), t2),
                        new Transactions.Transaction("a3", BigDecimal.valueOf(13), t3),
                        new Transactions.Transaction("a4", BigDecimal.valueOf(14), t4),
                        new Transactions.Transaction("a5", BigDecimal.valueOf(15), t5)
                )
        );
        Assertions.assertThat(result)
                .containsEntry("a2", BigDecimal.valueOf(12))
                .containsEntry("a3", BigDecimal.valueOf(13))
                .containsEntry("a4", BigDecimal.valueOf(14))
                .hasSize(3);
    }
}