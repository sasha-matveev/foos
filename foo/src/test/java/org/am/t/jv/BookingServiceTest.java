package org.am.t.jv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.am.t.jv.BookingService.*;
import static org.assertj.core.api.Assertions.assertThat;

class BookingServiceTest {

    IBookingService bookingService;

    @BeforeEach
    void setUp() {
        Seat s1 = new Seat();
        Seat s2 = new Seat();
        Seat s3 = new Seat();
        Seat s4 = new Seat();


        FLight f1 = new FLight(Map.of("s1", s1, "s2", s2));
        FLight f2 = new FLight(Map.of("s3", s3, "s4", s4));
        bookingService = new Service(Map.of("f1", f1, "f2", f2));

    }

    @Test
    void shouldBookOnlyOnce() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.book("f1", "s1", "u2")).isEqualTo(ActionResult.BOOKED_EXTERNALLY);
    }

    @Test
    void shouldBookAfterRelease() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.cancel("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.book("f1", "s1", "u2")).isEqualTo(ActionResult.OK);
    }

    @Test
    void shouldNotCancelExternalSeat() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.cancel("f1", "s1", "u2")).isEqualTo(ActionResult.BOOKED_EXTERNALLY);
    }

    @Test
    void shouldGetBookedSeatsEmpty() {
        assertThat(bookingService.getBookedSeats("f1")).satisfies(
                r -> assertThat(r.isOk()).isTrue(),
                r -> assertThat(r.getResult()).isEmpty()
        );
    }

    @Test
    void shouldGetBookedSeatsBooked() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.getBookedSeats("f1")).satisfies(
                r -> assertThat(r.isOk()).isTrue(),
                r -> assertThat(r.getResult())
                        .hasSize(1)
                        .containsEntry("s1", "u1")
        );
    }

    @Test
    void shouldGetBookedSeatsBookedAndCancelled() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.book("f1", "s2", "u2")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.getBookedSeats("f1")).satisfies(
                r -> assertThat(r.isOk()).isTrue(),
                r -> assertThat(r.getResult())
                        .hasSize(2)
                        .containsEntry("s1", "u1")
                        .containsEntry("s2", "u2")
        );

        assertThat(bookingService.cancel("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.getBookedSeats("f1")).satisfies(
                r -> assertThat(r.isOk()).isTrue(),
                r -> assertThat(r.getResult())
                        .hasSize(1)
                        .containsEntry("s2", "u2")
        );
    }

    @Test
    void shouldErrorOnGetBookedSeatsOnWrongFligh() {
        assertThat(bookingService.getBookedSeats("f3")).satisfies(
                r -> assertThat(r.isOk()).isFalse(),
                r -> assertThat(r.getError()).contains("f3")
        );
    }

    @Test
    void shouldBookTwiceWithSameUser() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.NO_CHANGES);
    }

    @Test
    void shouldCancelNoBooking() {
        assertThat(bookingService.cancel("f1", "s1", "u1")).isEqualTo(ActionResult.NO_CHANGES);
    }

    @Test
    void shouldCancelTwiceWithSameUser() {
        assertThat(bookingService.book("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.cancel("f1", "s1", "u1")).isEqualTo(ActionResult.OK);
        assertThat(bookingService.cancel("f1", "s1", "u1")).isEqualTo(ActionResult.NO_CHANGES);
    }

    @Test
    void shouldReturnInvalidCodes() {
        assertThat(bookingService.book("f3", "s1", "u1")).isEqualTo(ActionResult.INVALID_PLACE);
        assertThat(bookingService.book("f1", "s3", "u1")).isEqualTo(ActionResult.INVALID_PLACE);
    }
}