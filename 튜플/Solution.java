package KAKAOBLINDRECRUIMENT.튜플;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {

        print(new Solution().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"));
        print(new Solution().solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"));
        print(new Solution().solution("{{20,111},{111}}"));
        print(new Solution().solution("{{123}}"));
        print(new Solution().solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"));
    }

    public static void print(int[] arr) {

        for(int value : arr)
            System.out.print(value + " ");
        System.out.println();
    }

    public int[] solution(String s) {

        ArrayList<int[]> tuples = new ArrayList<>();
        ArrayList<Integer> answer = new ArrayList<>();
        int index = 0;

        s = s.replaceAll("[{]", "");
        StringTokenizer st = new StringTokenizer(s, "}");

        while(st.hasMoreTokens()) {

            String tupleStr = st.nextToken().replaceAll("[,]", " ").trim();
            StringTokenizer tupleSt = new StringTokenizer(tupleStr);

            int[] tupleInt = new int[tupleSt.countTokens()];
            int i = 0;

            while(tupleSt.hasMoreTokens())
                tupleInt[i++] = Integer.parseInt(tupleSt.nextToken());
            tuples.add(tupleInt);
        }
        tuples.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1.length - o2.length;
            }
        });
        answer.add(tuples.remove(0)[0]);

        for(int[] tuple : tuples) {

            ArrayList<Integer> temp = new ArrayList<>();

            for(int number : tuple) {

                if(answer.contains(number))
                    continue;

                temp.add(number);
            }
            answer.addAll(temp);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
