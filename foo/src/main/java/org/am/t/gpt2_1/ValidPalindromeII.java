package org.am.t.gpt2_1;

public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, 1);
    }

    private boolean validPalindrome(String s, int l, int r, int mistakesAllowed) {
        while (l <= r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return mistakesAllowed > 0
                        && (validPalindrome(s, l + 1, r, mistakesAllowed - 1) || validPalindrome(s, l, r - 1, mistakesAllowed - 1));
            }
        }
        return true;
    }
}
