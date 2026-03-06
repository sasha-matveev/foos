package org.am.arrays2;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray s = new FindAllNumbersDisappearedInAnArray();
        System.out.println(s.findDisappearedNumbers(new  int[]{4,3,2,7,8,2,3,1}));
        System.out.println(s.findDisappearedNumbers(new  int[]{1,1}));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] correct = new int[nums.length];
        for (int i = 0; i < correct.length; i++) {
            correct[i] = i + 1;
        }
        for (int num : nums) {
            int idx = num - 1;
            if (correct[idx] > 0) {
                correct[idx] = 0;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int j : correct) {
            if (j > 0) {
                ans.add(j);
            }
        }
        return ans;
    }


    public List<Integer> findDisappearedNumbers1(int[] nums) {
        return List.of();
    }
}
