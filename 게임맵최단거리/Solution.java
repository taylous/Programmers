package Problems.게임맵최단거리;

import java.util.PriorityQueue;

public class Solution {

    private int[] loX = {-1, 0, 1, 0};
    private int[] loY = {0, 1, 0, -1};

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}));
        System.out.println(new Solution().solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 0}, {0, 0, 0, 0, 1}}));
        System.out.println(new Solution().solution(new int[][]{{1, 1}}));
    }

    public int solution(int[][] maps) {

        int n = maps.length;
        int m = maps[0].length;

        int answer = 0;

        PriorityQueue<Location> locations = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][m];

        locations.offer(new Location(0, 0, 1));
        visited[0][0] = true;

        while (!locations.isEmpty()) {

            Location location = locations.poll();
            int x = location.x;
            int y = location.y;
            int c = location.c;

            if (x == n - 1 && y == m - 1) {

                answer = c;
                break;
            }

            for (int i = 0; i < 4; i++) {

                int nx = x + loX[i];
                int ny = y + loY[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (maps[nx][ny] == 0 || visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                locations.offer(new Location(nx, ny, c + 1));
            }
        }
        return answer == 0 ? -1 : answer;
    }
}

class Location implements Comparable<Location> {

    int x;
    int y;
    int c;

    public Location(int x, int y, int c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    @Override
    public int compareTo(Location other) {
        return this.c - other.c;
    }
}
