package org.am.t.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeOfFunctions {

    public static void main(String[] args) {
        ExclusiveTimeOfFunctions s = new ExclusiveTimeOfFunctions();
        System.out.println(Arrays.toString(s.exclusiveTime(2, List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6")))); // 3 4
        System.out.println(Arrays.toString(s.exclusiveTime(1, List.of("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7")))); // 8
        System.out.println(Arrays.toString(s.exclusiveTime(2, List.of("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7")))); // 7 1

    }


    public int[] exclusiveTime(int n, List<String> logs) {
        int[] funcs = new int[n];
        Stack<Log> stack = new Stack<>();
        Log lastLog = null;
        for (String str : logs) {
            Log log = new Log(str);
            if (stack.isEmpty()) {
                stack.push(log);
            } else {
                switch (log.op) {
                    case start -> {
                        Log prev = stack.peek();
                        funcs[prev.id] += log.timestamp - lastLog.timestamp;
                        stack.push(log);
                    }
                    case end -> {
                        stack.pop();
                        funcs[log.id] += log.timestamp - lastLog.timestamp;
                    }
                }
            }
            lastLog = log;
        }

        return funcs;
    }

    private static class Log {
        int id;
        Op op;
        int timestamp;

        public Log(String log) {
            String[] split = log.split(":");
            id = Integer.parseInt(split[0]);
            op = Op.valueOf(split[1]);
            timestamp = Integer.parseInt(split[2]) + (op == Op.end ? 1 : 0);
        }
    }

    enum Op {
        start,
        end,
    }
}
