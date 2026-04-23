package org.am.t.gpt2_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String str : strs) {
            res.computeIfAbsent(anagramKey(str), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(res.values());
    }

    private String anagramKey(String str) {
        int[] cnt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i) - 'a']++;
        }
        StringBuilder b = new StringBuilder();
        for (int i : cnt) {
            b.append(i).append("#");
        }
        return b.toString();
    }
}
