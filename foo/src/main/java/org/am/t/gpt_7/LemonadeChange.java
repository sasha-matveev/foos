package org.am.t.gpt_7;

public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int f = 0;
        int t = 0;
        for (int bill : bills) {
            if (bill == 5) {
                f++;
            } else if (bill == 10) {
                if (f > 0) {
                    f--;
                    t++;
                } else {
                    return false;
                }
            } else {
                // bill = 20
                if (f > 0 && t > 0) {
                    f--;
                    t--;
                } else if (f > 2) {
                    f -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
