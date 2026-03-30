package org.am.t.gpt_1;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        GroupAnagrams s = new GroupAnagrams();
        System.out.println(s.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})); // [["bat"],["nat","tan"],["ate","eat","tea"]]
        System.out.println(s.groupAnagrams(new String[]{""}));
        System.out.println(s.groupAnagrams(new String[]{"a"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            map.computeIfAbsent(key(str),k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    String key(String string) {
        int[] key = new int[26];
        for (int i = 0; i < string.length(); i++) {
            key[string.charAt(i) - 'a']++;
        }
        return Arrays.toString(key);
    }

}
