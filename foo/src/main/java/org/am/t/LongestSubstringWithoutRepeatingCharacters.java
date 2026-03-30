package org.am.t;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/description/">leetcode</a>
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(new  LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new  LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new  LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new  LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("123456789"));
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character>[] candidates = new HashSet[s.length()];
        int lastFinished = 0;

        for (int i = 0; i < s.length(); i++) {
            char sym = s.charAt(i);

            candidates[i] = new HashSet<>();

            for (int j = lastFinished; j <= i; j++) {
                if (!candidates[j].add(sym)) {
                    lastFinished = j;
                }
            }
        }

        int longest = 0;
        for (Set<Character> candidate : candidates) {
            longest = Math.max(longest, candidate.size());
        }

        return longest;
    }
}
