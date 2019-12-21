package a;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    ArrayList<String> path;

    public static void main(String[] args) {

        int[][] answer = new Solution().solution(2);

        for(int i = 0; i < answer.length; i++)
            System.out.println("[" + answer[i][0] + ", " + answer[i][1] + "]");
    }

    public int[][] solution(int n) {

        StringTokenizer st;
        int[][] answer;
        int idx = 0;

        this.path = new ArrayList<>();
        solve(n, 1, 2, 3);

        answer = new int[this.path.size()][2];

        for(String str : this.path) {

            st = new StringTokenizer(str, ",");

            answer[idx][0] = Integer.parseInt(st.nextToken());
            answer[idx++][1] = Integer.parseInt(st.nextToken());
        }
        return answer;
    }

    private void solve(int n, int first, int second, int third) {

        if(n == 1) {

            this.path.add(first + "," + third);
            return;
        }
        solve(n - 1, first, third, second);
        solve(1, first, second, third);
        solve(n - 1, second, first, third);
    }
}
