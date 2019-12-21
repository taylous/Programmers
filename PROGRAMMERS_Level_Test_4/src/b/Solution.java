package b;

import java.util.ArrayDeque;

public class Solution {

    int[][] land;

    int[] loX = { -1, 0, 1, 0 };
    int[] loY = { 0, 1, 0, -1 };

    int Answer;
    int N;
    int H;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[][]{
                {10, 11, 10, 11},
                {2, 21, 20, 10},
                {1, 20, 21, 11},
                {2, 1, 2, 1}
        }, 1));
    }

    public int solution(int[][] land, int height) {

        this.land = land;
        this.N = land.length;
        this.H = height;
        this.Answer = Integer.MAX_VALUE;

        solve(new boolean[N][N], 0, 0, (N * N) - 1);
        return Answer;
    }

    private int solve(boolean[][] copy, int startX, int startY, int remain) {

        System.out.println("[" + startX + ", " + startY + "] r: " + remain);
        if(startX == N - 1 && startY == N - 1 && remain == 0)
            return 0;

        boolean[][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++)
            System.arraycopy(copy[i], 0, visited[i], 0, N);

        ArrayDeque<Location> arrayDeque = new ArrayDeque<>();
        Location location;
        int x, y, t, nx, ny;
        int r = remain;
        int ret = 0;

        arrayDeque.add(new Location(startX, startY));
        visited[startX][startY] = true;

        while(!arrayDeque.isEmpty()) {

            location = arrayDeque.remove();
            x = location.x;
            y = location.y;
            t = location.t;

            for (int i = 0; i < 4; i++) {

                nx = x + loX[i];
                ny = y + loY[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                    continue;
                if(Math.abs(land[x][y] - land[nx][ny]) > H)
                    continue;

                r--;
                visited[nx][ny] = true;
                arrayDeque.add(new Location(nx, ny, t));
            }
        }

        for(int i = 0; i < N; i++) {

            for(int j = 0; j < N; j++) {

                if(!visited[i][j])
                    continue;

                for(int k = 0; k < 4; k++) {

                    nx = i + loX[k];
                    ny = j + loY[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                        continue;

                    ret = Math.min(ret, solve(visited, nx, ny, r - 1) + Math.abs(land[i][j] - land[nx][ny]));
                }
            }
        }
    }
}

class Location {

    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}