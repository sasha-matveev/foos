package org.am.t.gpt_2;

public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome s = new ValidPalindrome();
        System.out.println(s.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(s.isPalindrome("race a car"));
        System.out.println(s.isPalindrome(" "));

    }

    public boolean isPalindrome(String s) {
        if (s.isBlank() || s.length() == 1) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l <= r) {
            char lC = s.charAt(l);
            char rC = s.charAt(r);
            if (!Character.isLetterOrDigit(lC)) {
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(rC)) {
                r--;
                continue;
            }
            if (Character.toLowerCase(lC) != Character.toLowerCase(rC)) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }
}
