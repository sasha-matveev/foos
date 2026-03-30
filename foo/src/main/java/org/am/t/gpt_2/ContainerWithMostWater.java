package org.am.t.gpt_2;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater s = new ContainerWithMostWater();
        System.out.println(s.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(s.maxArea(new int[]{1, 1}));
        System.out.println(s.maxArea(new int[]{2, 0}));
    }

    public int maxArea(int[] height) {
        int maxVol = 0;

        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            maxVol = Math.max(maxVol, (r - l) * Math.min(height[l], height[r]));
            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return maxVol;
    }

    public int maxArea_1(int[] height) {
        int[] volumes = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            int maxVol = 1;
            for (int j = height.length - 1; j > i; j--) {
                int vol = (j - i) * Math.min(height[i], height[j]);
                maxVol = Math.max(maxVol, vol);
                if (height[j] >= height[i]) {
                    break;
                }
            }
            volumes[i] = maxVol;
        }

        int maxVol = 1;
        for (int volume : volumes) {
            maxVol = Math.max(maxVol, volume);
        }
        return maxVol;
    }

}
