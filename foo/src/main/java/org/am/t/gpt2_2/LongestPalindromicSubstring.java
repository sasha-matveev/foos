package org.am.t.gpt2_2;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        int longestPalindromeStart = -1;
        int longestPalindromeLength = -1;
        for (int i = 0; i < s.length(); i++) {
            int l1 = palindrome(s, i, i);
            if (l1 > longestPalindromeLength) {
                longestPalindromeStart = i - (l1 - 1) / 2; // 1 - 1  = 0
                longestPalindromeLength = l1; // 3
            }
            int l2 = palindrome(s, i, i + 1);
            if (l2 > longestPalindromeLength) {
                longestPalindromeStart = i + 1 - l2 / 2;
                longestPalindromeLength = l2;
            }
        }
        return s.substring(longestPalindromeStart, longestPalindromeStart + longestPalindromeLength);
    }

    private int palindrome(String s, int l, int r) {
        int length = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            length += l == r ? 1 : 2;
            l--;
            r++;
        }
        return length;
    }

}
