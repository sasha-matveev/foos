package org.am.t.gpt_1;

import java.util.HashMap;

public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram s = new ValidAnagram();
        System.out.println(s.isAnagram("anagram", "nagaram"));
        System.out.println(s.isAnagram("rat", "cat"));
    }

    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> sMap = new HashMap<>();
        HashMap<Character, Integer> tMap = new HashMap<>();
        char[] str = s.toCharArray();
        for (char c : str) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        char[] ttr = t.toCharArray();
        for (char x : ttr) {
            tMap.put(x, tMap.getOrDefault(x, 0) + 1);
        }
        return sMap.equals(tMap);
    }
}
