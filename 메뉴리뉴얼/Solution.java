package Problems.메뉴리뉴얼;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Solution {

    private HashMap<String, Integer> treeMap;
    private int minLen, maxLen;

    public String[] solution(String[] orders, int[] course) {

        treeMap = new HashMap<>();
        ArrayList<ArrayList<String>> counts = new ArrayList<>();
        ArrayList<String> answer = new ArrayList<>();

        minLen = course[0];
        maxLen = course[course.length - 1];

        for (String order : orders) {

            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            combinations(chars, new StringBuilder(), 0);
        }
        for (int i = 0; i <= 10; i++)
            counts.add(new ArrayList<>());

        int[] countArray = new int[11];
        for (String key : treeMap.keySet()) {

            int c = treeMap.get(key);

            if (c >= 2) {

                int len = key.length();

                if (countArray[len] < c) {

                    counts.get(len).clear();
                    counts.get(len).add(key);
                    countArray[len] = treeMap.get(key);
                } else if (countArray[len] == c) {

                    counts.get(len).add(key);
                }
            }
        }
        for (int offset : course)
            answer.addAll(counts.get(offset));
        Collections.sort(answer);
        return answer.toArray(String[]::new);
    }

    private void combinations(char[] order, StringBuilder key, int idx) {

        if (idx == order.length) {

            if (minLen > key.length() || maxLen < key.length())
                return;
            treeMap.put(key.toString(), treeMap.containsKey(key.toString()) ? treeMap.get(key.toString()) + 1 : 1);
            return;
        }
        combinations(order, key, idx + 1);

        key.append(order[idx]);
        combinations(order, key, idx + 1);
        key.deleteCharAt(key.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
}
