class Solution {

    private int[][] triangle;
    private int[][] cache;
    private int N;
    
    public int solve(int row, int col, int boundary) {

        if(row == N)
            return 0;

        if(cache[row][col] != 0)
            return cache[row][col];

        cache[row][col] = Math.max(cache[row][col], solve(row + 1, col, boundary + 1) + triangle[row][col]);
        if(col + 1 <= boundary + 1)
            cache[row][col] = Math.max(cache[row][col], solve(row + 1, col + 1, boundary + 1) + triangle[row][col]);
        return cache[row][col];
    }

    public int solution(int[][] triangle) {

        this.N = triangle.length;
        this.triangle = triangle;
        this.cache = new int[N][N];
        return solve(0, 0, 0);
    }
}