package org.am.monstack;

import java.util.Arrays;

public class FinalPricesWithASpecialDiscountInAShop {

    public static void main(String[] args) {
        FinalPricesWithASpecialDiscountInAShop s = new FinalPricesWithASpecialDiscountInAShop();
        System.out.println(Arrays.toString(s.finalPrices(new int[]{8, 4, 6, 2, 3})));
        System.out.println(Arrays.toString(s.finalPrices(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(s.finalPrices(new int[]{10, 1, 1, 6})));
    }

    public int[] finalPrices(int[] prices) {
        int[] res = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            var price = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= price) {
                    price = price - prices[j];
                    break;
                }
            }
            res[i] = price;
        }
        return res;
    }

}
