package KAKAOBLINDRECRUIMENT.후보키;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    private ArrayList<Integer> candidates = new ArrayList<>();

    private String[][] rel;
    private int rowCount;
    private int columnCount;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new String[][]
                {
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}
                }
        ));
    }

    public int extract(int mask) {

        Set<String> set = new HashSet<>();
        boolean flag = false;

        for(int candidate : candidates) {

            if((candidate & mask) == candidate) {
                flag = true;
                break;
            }
        }
        if(flag)
            return 0;

        for(int row = 0; row < rowCount; row++) {

            StringBuilder comb = new StringBuilder();

            for(int column = 0; column < columnCount; column++) {
                if ((mask & (1 << column)) > 0)
                    comb.append(rel[row][column]);
            }

            if(set.contains(comb.toString()))
                return 0;
            set.add(comb.toString());
        }
        candidates.add(mask);
        return 1;
    }

    public int combinations(int mask, int idx) {

        if(idx >= columnCount)
            return extract(mask);

        int ret = 0;
        ret += combinations(mask, idx + 1);
        ret += combinations(mask | (1 << idx), idx + 1);
        return ret;
    }

    public int solution(String[][] relation) {

        rowCount = relation.length;
        columnCount = relation[0].length;
        rel = relation;

        return combinations(0, 0);
    }
}
