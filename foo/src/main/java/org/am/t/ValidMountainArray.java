package org.am.t;

public class ValidMountainArray {
    public static void main(String[] args) {
        ValidMountainArray s = new ValidMountainArray();
        System.out.println(s.validMountainArray(new int[] {2,1}));
        System.out.println(s.validMountainArray(new int[] {3,5,5}));
        System.out.println(s.validMountainArray(new int[] {0,3,2,1}));
        System.out.println(s.validMountainArray(new int[] {0,1,2,3,4,5,6,7,8,9}));
        System.out.println(s.validMountainArray(new int[] {5,4,3,2,1}));
    }


    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return false;
        }
        if (arr[0] > arr[1]) {
            return false;
        }
        boolean inc = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] == arr[i]) {
                return false;
            }
            if (inc && arr[i] < arr[i - 1]) {
                inc = false;
                continue;
            }
            if (!inc && arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
