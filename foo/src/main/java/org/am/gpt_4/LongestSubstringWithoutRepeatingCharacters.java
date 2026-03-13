package org.am.gpt_4;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters s = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(s.lengthOfLongestSubstring("bbbbb"));
        System.out.println(s.lengthOfLongestSubstring("pwwkew"));
        System.out.println(s.lengthOfLongestSubstring("123456789"));
        System.out.println(s.lengthOfLongestSubstring(" ")); // !
        System.out.println(s.lengthOfLongestSubstring("aab")); // !
        System.out.println(s.lengthOfLongestSubstring("qrsvbspk")); // !
    }

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        HashSet<Character> chars = new HashSet<>();
        int maxSize = 0;
        while (r < s.length()) {
            char rC = s.charAt(r++);
            if (chars.add(rC)) {
                maxSize = Math.max(maxSize, chars.size());
            } else {
                while (s.charAt(l) != rC) {
                    chars.remove(s.charAt(l));
                    l++;
                }
                l++;
            }
        }
        return maxSize;
    }

    public int lengthOfLongestSubstring_wrong2(String s) {
        int l = 0;
        int r = 0;
        HashSet<Character> chars = new HashSet<>();
        int maxSize = 0;
        while (r < s.length()) {
            char rC = s.charAt(r++);
            if (chars.add(rC)) {
                maxSize = Math.max(maxSize, chars.size());
            } else {
                char lC = s.charAt(l++);
                if (rC != lC) {
                    chars.remove(lC);
                }
            }
        }
        return maxSize;
    }

    public int lengthOfLongestSubstring_wrong1(String s) {
        int l = 0;
        int r = 0;
        HashSet<Character> chars = new HashSet<>();
        int maxSize = 0;
        while (r < s.length()) {
            if (chars.add(s.charAt(r++))) {
                maxSize = Math.max(maxSize, chars.size());
            } else {
                chars.remove(s.charAt(l++));
            }
        }
        return maxSize;
    }

}
