package Problems.보행자천국;

import java.util.Arrays;

public class Solution {

    private final int MOD = 20170805;

    private int[] loX = { 1, 0};
    private int[] loY = { 0, 1};

    private int[][][] cache;
    private int N;
    private int M;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, 3, new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(new Solution().solution(3, 6, new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }

    public int pedestrianHeaven(int[][] cityMap, int x, int y, int pre) {

        if (x == N - 1 && y == M - 1)
            return 1;
        if (cache[x][y][pre] != -1)
            return cache[x][y][pre];
        cache[x][y][pre] = 0;

        int nx, ny;
        for (int i = 0; i < 2; i++) {

            nx = x + loX[i];
            ny = y + loY[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;

            if(cityMap[x][y] == 0 || (cityMap[x][y] == 2 && i == pre))
                cache[x][y][pre] += pedestrianHeaven(cityMap, nx, ny, i) % MOD;
        }
        return cache[x][y][pre] % MOD;
    }

    public int solution(int m, int n, int[][] cityMap) {

        this.cache = new int[m][n][2];
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                Arrays.fill(this.cache[i][j], -1);

        this.N = m;
        this.M = n;
        return pedestrianHeaven(cityMap, 0, 0, 0);
    }
}
