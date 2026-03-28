package org.am.gpt_7;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (char task : tasks) {
            freq.put(
                    task,
                    freq.getOrDefault(task, 0) + 1
            );
            maxFreq = Math.max(maxFreq, freq.get(task));
        }
        int countMaxFreq = 0;
        for (Integer value : freq.values()) {
            if (value == maxFreq) {
                countMaxFreq++;
            }
        }

        return Math.max(tasks.length, (maxFreq - 1) * (n + 1) + countMaxFreq);
    }
}
