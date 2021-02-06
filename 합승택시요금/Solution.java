package Problems.합승택시요금;

import java.util.Arrays;

public class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {

        int[][] paths = new int[n + 1][n + 1];
        int INF = 987654321;
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            Arrays.fill(paths[i], INF);
            paths[i][i] = 0;
        }

        for (int[] fare : fares) {
            paths[fare[0]][fare[1]] = fare[2];
            paths[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (paths[i][j] > paths[i][k] + paths[k][j])
                        paths[i][j] = paths[i][k] + paths[k][j];
                }
            }
        }
        for (int sharedPoint = 1; sharedPoint <= n; sharedPoint++) {

            if (paths[s][sharedPoint] != INF && paths[sharedPoint][a] != INF && paths[sharedPoint][b] != INF)
                answer = Math.min(answer, paths[s][sharedPoint] + paths[sharedPoint][a] + paths[sharedPoint][b]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
        System.out.println(new Solution().solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
        System.out.println(new Solution().solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
    }
}
