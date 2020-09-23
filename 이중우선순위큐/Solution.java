package Problems.이중우선순위큐;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"I 16","D 1"})));
        System.out.println(Arrays.toString(new Solution().solution(new String[]{"I 7","I 5","I -5","D -1"})));
    }

    public int[] solution(String[] operations) {

        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st;

        for(String operation : operations) {

            st = new StringTokenizer(operation);

            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if(command.equals("I")) {
                list.add(value);
            }
            else {

                if(list.isEmpty())
                    continue;
                if(value == 1)
                    list.removeLast();
                else
                    list.removeFirst();
            }
            Collections.sort(list);
        }
        int max = list.isEmpty() ? 0 : list.removeLast();
        int min = list.isEmpty() ? 0 : list.removeFirst();
        return new int[]{max, min};
    }
}
