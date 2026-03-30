package org.am.t.gpt_3;

public class ValidPerfectSquare {

    public static void main(String[] args) throws InterruptedException {
        ValidPerfectSquare s = new ValidPerfectSquare();
        System.out.println(s.isPerfectSquare(2147483647));
    }

    public boolean isPerfectSquare(int num) throws InterruptedException {
        int l = 0;
        int r = num;

        while (l <= r) {
            int m = (l + (r - l) / 2);
            long sqr = ((long) m) * m;
            if (sqr == num) {
                return true;
            } else if (sqr > num) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

    public boolean isPerfectSquare_slow(int num) {
        int l = 0;
        int r = num;

        while (l <= r) {
            int m = l + (r - l) / 2;
            int sqr = m * m;
            if (sqr == num) {
                return true;
            } else if (sqr > num) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return false;
    }

}
