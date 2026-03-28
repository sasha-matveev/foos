package org.am.jv;

import java.util.concurrent.ConcurrentHashMap;

public class DeduplicatingTaskExecutor {

    private final ConcurrentHashMap<String, Boolean> tasks = new ConcurrentHashMap<>();

    boolean submit(String taskId, Runnable task) {
        if ( tasks.putIfAbsent(taskId, true) == null) {
            try {
                task.run();
            } catch (Exception e) {
                tasks.remove(taskId);
                throw e;
            }
            return true;
        } else {
            return false;
        }
    }
}
