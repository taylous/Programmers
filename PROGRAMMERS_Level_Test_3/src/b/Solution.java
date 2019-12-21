package b;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Solution().solution(3, 10)));
    }

    public int[] solution(int n, int s) {

        int[] answer = null;
        int idx, remain;

        if(s / n == 0) {

            answer = new int[]{ -1 };
        }
        else {

            answer = new int[n];
            for(int i = 0; i < n; i++)
                answer[i] = s / n;

            idx = n - 1;
            remain = s % n;

            while(remain-- > 0)
                answer[idx--] += 1;
        }
        return answer;
    }
}
