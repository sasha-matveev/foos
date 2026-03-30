package org.am.t.gpt_6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<WorkerInterval> queue = new PriorityQueue<>(Comparator.comparingInt(wi -> wi.interval.start));
        Interval current = null;

        for (int i = 0; i < schedule.size(); i++) {
            queueNext(i, schedule, queue);
        }
        List<Interval> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            WorkerInterval next = queue.poll();
            if (current == null) {
                current = next.interval;
            } else if (current.endsEarlierThanStartOf(next.interval)) {
                result.add(new Interval(
                        current.end,
                        next.interval.start
                ));
                current = next.interval;
            } else {
                current = current.mergeEnds(next.interval);
            }
            queueNext(next.workerId, schedule, queue);
        }
        return result;
    }

    private void queueNext(int workerId, List<List<Interval>> schedule, PriorityQueue<WorkerInterval> queue) {
        List<Interval> intervals = schedule.get(workerId);
        if (!intervals.isEmpty()) {
            queue.add(new WorkerInterval(intervals.removeFirst(), workerId));
        }
    }

    record Interval(int start, int end) {
        private boolean endsEarlierThanStartOf(Interval next) {
            return this.end < next.start;
        }

        private Interval mergeEnds(Interval next) {
            return new Interval(this.start, Math.max(this.end, next.end));
        }
    }

    record WorkerInterval(
            Interval interval,
            Integer workerId
    ) {
    }
}