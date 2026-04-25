package org.am.t.gpt2_3;

import java.util.HashMap;

public class _4SumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> sumCnt = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                sumCnt.compute(n1 + n2, (k, v) -> v == null ? 1 : (v + 1));
            }
        }

        int res = 0;
        for (int n3 : nums3) {
            for (int n4 : nums4) {
                if (sumCnt.containsKey(-n3 -n4 )){
                    res += sumCnt.get(-n3 - n4);
                }
            }
        }

        return res;
    }
}
