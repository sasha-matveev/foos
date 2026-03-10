package org.am.gpt_2;

public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater s = new TrappingRainWater();
        System.out.println(6 == s.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(9 == s.trap(new int[]{4, 2, 0, 3, 2, 5}));
        System.out.println(s.trap(new int[]{1, 0, 3}));
        System.out.println(s.trap(new int[]{2, 0, 1, 2}));
        System.out.println(s.trap(new int[]{1, 0}));
        System.out.println(s.trap(new int[]{1}));
    }

    public int trap(int[] height) {
        int total = 0;

        int l = 0;
        int r = height.length - 1;

        int lMax = 0;
        int rMax = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);

            if (lMax <= rMax) {
                total += lMax - height[l];
                l++;
            } else {
                total += rMax - height[r];
                r--;
            }
        }

        return total;
    }

    public int trap_wrong(int[] height) {
        int total = 0;
        int current = 0;
        int l = 0;
        int r = 1;

        while (r < height.length) {
            if (height[r] >= height[l]) {
                total += current;
                current = 0;
                l = r;
            } else {
                current += height[l] - height[r];
            }
            r++;
        }

        return total;
    }

}
