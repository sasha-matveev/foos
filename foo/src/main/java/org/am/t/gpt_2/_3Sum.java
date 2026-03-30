package org.am.t.gpt_2;

import java.util.*;

public class _3Sum {
    public static void main(String[] args) {
        _3Sum s = new _3Sum();
        System.out.println(s.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(s.threeSum(new int[]{0, 1, 1}));
        System.out.println(s.threeSum(new int[]{0, 0, 0}));
        System.out.println(s.threeSum(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return List.of();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(List.of(nums[i], nums[l++], nums[r--]));
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
                while (l < r && l > i + 1 && nums[l - 1] == nums[l]) {
                    l++;
                }
                while (l < r && r < nums.length - 1 && nums[r + 1] == nums[r]) {
                    r--;
                }
            }
        }

        return res;
    }


    public List<List<Integer>> threeSum_not_optimal(int[] nums) {
        if (nums.length < 3) {
            return List.of();
        }

        Map<Integer, Integer> idxes = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            idxes.put(nums[i], i);
        }
        HashSet<List<Integer>> res = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                Integer add = idxes.get(-nums[i] - nums[j]);
                if (add != null && add != i && add != j) {
                    int[] triplet = {nums[i], nums[j], nums[add]};
                    Arrays.sort(triplet);
                    res.add(List.of(triplet[0], triplet[1], triplet[2]));
                }
            }
        }
        return new ArrayList<>(res);
    }


}
