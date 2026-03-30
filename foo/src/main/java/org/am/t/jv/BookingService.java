package org.am.t.jv;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class BookingService {
    public enum ActionResult {
        OK, NO_CHANGES, BOOKED_EXTERNALLY, INVALID_PLACE
    }

    public static class ViewResult<R> {
        private final String error;
        private final R result;

        private ViewResult(String error, R result) {
            this.error = error;
            this.result = result;
        }

        public static <X> ViewResult<X> ok(X result) {
            return new ViewResult<>(null, result);
        }

        public static <X> ViewResult<X> error(String error) {
            return new ViewResult<>(error, null);
        }

        public boolean isOk() {
            return error == null;
        }

        public String getError() {
            return error;
        }

        public R getResult() {
            if (isOk()) {
                return result;
            } else {
                throw new IllegalStateException("No result available because of error: " + error);
            }
        }
    }

    public static class Seat {
        private final AtomicReference<String> userId = new AtomicReference<>(null);

        public Seat() {
        }

        public boolean isFree() {
            return userId.get() == null;
        }

        public Optional<String> currentUser() {
            return Optional.ofNullable(userId.get());
        }

        public ActionResult book(String userId) {
            if (this.userId.compareAndSet(userId, userId)) {
                // booked by the same user
                return ActionResult.NO_CHANGES;
            }
            if (this.userId.compareAndSet(null, userId)) {
                return ActionResult.OK;
            }
            return ActionResult.BOOKED_EXTERNALLY;
        }

        public ActionResult cancel(String userId) {
            if (this.userId.compareAndSet(null, null)) {
                // not booked at all
                return ActionResult.NO_CHANGES;
            }
            if (this.userId.compareAndSet(userId, null)) {
                return ActionResult.OK;
            }
            return ActionResult.BOOKED_EXTERNALLY;
        }
    }

    public static class FLight {
        private final Map<String, Seat> seats;

        public FLight(Map<String, Seat> seats) {
            this.seats = seats;
        }

        public ActionResult book(String seatId, String userId) {
            return Optional.ofNullable(seats.get(seatId)).map(s -> s.book(userId)).orElse(ActionResult.INVALID_PLACE);
        }

        public ActionResult cancel(String seatId, String userId) {
            return Optional.ofNullable(seats.get(seatId)).map(s -> s.cancel(userId)).orElse(ActionResult.INVALID_PLACE);
        }

        public ViewResult<Map<String, String>> getBookedSeats() {
            Map<String, String> res = new HashMap<>();
            for (String s : this.seats.keySet()) {
                seats.get(s).currentUser().ifPresent(u -> res.put(s, u));
            }
            return ViewResult.ok(res);
        }
    }

    public interface IBookingService {
        ActionResult book(String flightId, String seatId, String userId);

        ActionResult cancel(String flightId, String seatId, String userId);

        ViewResult<Map<String, String>> getBookedSeats(String flightId);
    }


    public static class Service implements IBookingService {

        private final Map<String, FLight> flights;

        public Service(Map<String, FLight> flights) {
            this.flights = flights;
        }

        @Override
        public ActionResult book(String flightId, String seatId, String userId) {
            return Optional.ofNullable(flights.get(flightId))
                    .map(f -> f.book(seatId, userId))
                    .orElse(ActionResult.INVALID_PLACE);
        }

        @Override
        public ActionResult cancel(String flightId, String seatId, String userId) {
            return Optional.ofNullable(flights.get(flightId))
                    .map(f -> f.cancel(seatId, userId))
                    .orElse(ActionResult.INVALID_PLACE);
        }

        @Override
        public ViewResult<Map<String, String>> getBookedSeats(String flightId) {
            return Optional.ofNullable(flights.get(flightId))
                    .map(f -> f.getBookedSeats())
                    .orElse(ViewResult.error("no such flightId : " + flightId));
        }
    }

}
