package org.am.t.gpt_5;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder s = new MedianFinder();
        s.addNum(1);
        s.addNum(2);
        System.out.println(s.findMedian());
        s.addNum(3);
        System.out.println(s.findMedian());
    }

    private PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> max = new PriorityQueue<>();


    public MedianFinder() {

    }

    public void addNum(int num) {
        if (min.isEmpty() || min.peek() >= num) {
            min.add(num);
        } else {
            max.add(num);
        }
        while (min.size() - 1 > max.size()) {
            Integer x = min.poll();
            max.add(x);
        }
        while (max.size() > min.size()) {
            Integer x = max.poll();
            min.add(x);
        }
    }

    public double findMedian() {
        if (min.size() == max.size()) {
            return min.peek() + ((double) max.peek() - min.peek()) / 2;
        } else {
            return min.peek();
        }
    }

}
