package org.am.monstack;

import java.util.Arrays;
import java.util.Stack;

public class FinalPricesWithASpecialDiscountInAShop {

    public static void main(String[] args) {
        FinalPricesWithASpecialDiscountInAShop s = new FinalPricesWithASpecialDiscountInAShop();
        System.out.println(Arrays.toString(s.finalPrices(new int[]{8, 4, 6, 2, 3})));
        System.out.println(Arrays.toString(s.finalPrices(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(s.finalPrices(new int[]{10, 1, 1, 6})));
    }

    public int[] finalPrices(int[] prices) {
        int[] res = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++){
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]){
                int top = stack.pop();
                res[top] = prices[top] - prices[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int top = stack.pop();
            res[top] = prices[top];
        }
        return res;
    }

}
