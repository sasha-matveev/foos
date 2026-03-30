package org.am.t.gpt_5;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    public static void main(String[] args) throws InterruptedException {
        KthLargestElementInAnArray s = new KthLargestElementInAnArray();
        System.out.println(s.findKthLargest_quickselect(new int[]{3, 2, 1, 5, 6, 4}, 2));

        int[] huge = new int[20];
        Arrays.fill(huge, 1);
        for (int i = 0; i < 5; i++) {
            huge[i] = i;
            huge[huge.length - i - 1] = -i;
        }
        System.out.println(s.findKthLargest_quickselect(huge, 10));
    }

    public int findKthLargest_quickselect(int[] nums, int k) {
        int targetIdx = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int[] prt = partition(nums, l, r);
            if (targetIdx >= prt[0] && targetIdx <= prt[1]) {
                return nums[targetIdx];
            } else if (targetIdx < prt[0]) {
                r = prt[0] - 1;
            } else {
                l = prt[1] + 1;
            }
        }
        throw new RuntimeException();
    }

    private int[] partition(int[] ar, int l, int r) {
        int val = ar[r];
        int insertLeft = l;
        for (int i = l; i < r; i++) {
            if (ar[i] < val) {
                swap(ar, insertLeft, i);
                insertLeft++;
            }
        }
        swap(ar, insertLeft, r);
        int insertRight = insertLeft;
        for (int i = insertLeft; i <= r; i++) {
            if (ar[i] == val) {
                swap(ar, i, insertRight);
                insertRight++;
            }
        }
        return new int[]{insertLeft, insertRight - 1};
    }

    private void swap(int[] ar, int a, int b) {
        int t = ar[b];
        ar[b] = ar[a];
        ar[a] = t;
    }


    public int findKthLargest_minHeap(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

}
