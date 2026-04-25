package org.am.t.gpt2_3;

import java.util.HashSet;
import java.util.Set;

// time - O(n)
// space - O(n)
// empty array - return false - OK
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
