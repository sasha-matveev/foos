package org.am.t.stack;

public class EvaluateReversePolishNotation {
    public static void main(String[] args) {
        EvaluateReversePolishNotation s = new EvaluateReversePolishNotation();
        System.out.println(s.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(s.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(s.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));

    }

    public int evalRPN(String[] tokens) {
        MyStack stack = new MyStack();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int r = stack.pop();
                    int l = stack.pop();
                    stack.push(l / r);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    private static class MyStack {
        int[] array = new int[10000];
        int cur = -1;

        int pop() {
            return array[cur--];
        }

        void push(int x) {
            array[++cur] = x;
        }
    }

}
