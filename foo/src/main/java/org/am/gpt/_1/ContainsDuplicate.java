package org.am.gpt._1;

import java.util.HashMap;

public class ContainsDuplicate {
    public static void main(String[] args) {
        ContainsDuplicate s = new ContainsDuplicate();
        System.out.println(s.containsDuplicate(new int[]{1,2,3,1}));
        System.out.println(s.containsDuplicate(new int[]{1,2,3,4}));
        System.out.println(s.containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            if (map.put(num, true) != null) {
                return true;
            }
        }
        return false;
    }

}
