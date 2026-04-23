package org.am.a;

import java.time.Instant;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;

public class YandexJavaSkills {
    interface CallbackScheduler extends AutoCloseable {
        void schedule(Runnable callback, Instant when);
    }

    public static class CallbackSchedulerImpl implements CallbackScheduler {

        private final PriorityBlockingQueue<ScheduledTask> queue = new PriorityBlockingQueue<>();
        private volatile boolean stopped = false;
        private final Object monitor = new Object();
        private final Thread thread;

        public CallbackSchedulerImpl() {
            thread = new Thread(() -> {
                try {
                    long toWait = Long.MAX_VALUE;
                    while (!stopped) {
                        synchronized (monitor) {
                            monitor.wait(toWait);
                        }

                        if (stopped) {
                            return;
                        }

                        ScheduledTask task = queue.poll();

                        if (task == null) {
                            toWait = Long.MAX_VALUE;
                            continue;
                        }

                        long now = Instant.now().toEpochMilli();
                        long next = task.instant.toEpochMilli();

                        if (now >= next) {
                            try {
                                task.runnable.run();
                            } catch (Exception e) {
                                try {
                                    task.onException.accept(e);
                                } catch (Exception ex) {
                                    // log?
                                }
                            }
                            ScheduledTask nextT = queue.peek();
                            toWait = nextT == null
                                    ? Long.MAX_VALUE
                                    : Math.max(1, nextT.instant.toEpochMilli() - Instant.now().toEpochMilli());
                        } else {
                            queue.add(task);
                            toWait = next - now;
                        }
                    }
                } catch (InterruptedException e) {
                    //
                }
            });
            thread.start();
        }

        @Override
        public void schedule(Runnable callback, Instant when) {
            if (stopped) {
                throw new IllegalStateException("stopped");
            }
            queue.add(new ScheduledTask(callback, e -> {
            }, when));
            synchronized (monitor) {
                monitor.notify();
            }
        }

        @Override
        public void close() throws Exception {
            this.stopped = true;
            synchronized (monitor) {
                monitor.notify();
            }
        }

        class ScheduledTask implements Comparable<ScheduledTask> {
            private final Runnable runnable;
            private final Consumer<Exception> onException;
            private final Instant instant;

            ScheduledTask(Runnable runnable, Consumer<Exception> onException, Instant instant) {
                this.runnable = runnable;
                this.onException = onException;
                this.instant = instant;
            }

            @Override
            public int compareTo(ScheduledTask o) {
                return this.instant.compareTo(o.instant);
            }
        }
    }

    public static void main(String[] args) {


    }
}
