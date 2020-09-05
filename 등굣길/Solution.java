package KAKAO.등굣길;

public class Solution {

    private boolean[][] map;
    private int[][] cache;

    private int M;
    private int N;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(4, 3, new int[][]{{2, 2}}));
        System.out.println(new Solution().solution(4, 4, new int[][]{{1, 2}}));
    }

    public int result(int row, int col) {

        if(row == N - 1 && col == M - 1)
            return 1;

        if(cache[row][col] != 0)
            return cache[row][col];

        if(row + 1 < N && !map[row + 1][col])
            cache[row][col] += result(row + 1, col) % 1000000007;
        if(col + 1 < M && !map[row][col + 1])
            cache[row][col] += result(row, col + 1) % 1000000007;
        return cache[row][col] %= 1000000007;
    }

    public int solution(int m, int n, int[][] puddles) {

        this.map = new boolean[n][m];
        this.cache = new int[n][m];
        this.N = n;
        this.M = m;

        for(int[] puddle : puddles)
            this.map[puddle[1] - 1][puddle[0] - 1] = true;
        return result(0, 0);
    }
}
