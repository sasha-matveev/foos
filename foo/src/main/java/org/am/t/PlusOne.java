package org.am.t;

import java.util.Arrays;

public class PlusOne {

    public static void main(String[] args) {
        PlusOne s = new PlusOne();
        System.out.println(Arrays.toString(s.plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(s.plusOne(new int[]{9})));
        System.out.println(Arrays.toString(s.plusOne(new int[]{2, 9})));
    }


    public int[] plusOne(int[] digits) {
        int rest = 1;
        int[] res = new int[digits.length];
        for (int i = digits.length - 1; i >=0; i--) {
            int v =  digits[i] + rest;
            res[i] =  v % 10;
            rest = v / 10;
        }
        if (rest > 0) {
            int[] res2 =  new int[digits.length + 1];
            System.arraycopy(res, 0, res2, 1, digits.length);
            res2[0] = rest;
            return res2;
        }
        return res;
    }
}
