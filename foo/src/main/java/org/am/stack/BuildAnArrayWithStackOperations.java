package org.am.stack;

import java.util.ArrayList;
import java.util.List;

public class BuildAnArrayWithStackOperations {

    public static void main(String[] args) {
        BuildAnArrayWithStackOperations s = new BuildAnArrayWithStackOperations();
        System.out.println(s.buildArray(new int[]{1, 3}, 3));
        System.out.println(s.buildArray(new int[]{1,2,3}, 3));
        System.out.println(s.buildArray(new int[]{1,2}, 4));
    }

    public List<String> buildArray(int[] target, int n) {
        ArrayList<String> strings = new ArrayList<>();
        String push = "Push";
        String pop = "Pop";
        int str = 1;
        for (int num : target) {
            while (str != num) {
                strings.add(push);
                strings.add(pop);
                str++;
            }
            strings.add(push);
            str++;
        }

        return strings;
    }
}
