package Problems.블록게임;

import java.util.*;

public class Solution {

    private ArrayList<int[][]> blackBlocks;
    private int[][] board;

    private final int[] loX = { -1, 0, 1, 0 };
    private final int[] loY = { 0, 1, 0, -1 };

    private final int[][][] blocks = {
            {
                    {1, 0},
                    {1, 1},
                    {1, 2}
            },
            {
                    {1, 0},
                    {2, 0},
                    {2, -1}
            },
            {
                    {1, 0},
                    {2, 0},
                    {2, 1}
            },
            {
                    {1, 0},
                    {1, -1},
                    {1, -2}
            },
            {
                    {1, 0},
                    {1, -1},
                    {1, 1}
            }
    };
    private int N;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0}, {0, 0, 0, 0, 3, 0, 4, 0, 0, 0}, {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}));
        System.out.println(new Solution().solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 4, 0, 0, 0}, {0, 0, 0, 0, 0, 4, 4, 0, 0, 0}, {0, 6, 0, 0, 3, 0, 4, 0, 0, 0}, {6, 6, 6, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5}, {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}));
        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 2, 2, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0}}));
        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 0, 2, 0},
                {1, 2, 2, 2, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}}));
        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0, 0},
                {2, 2, 0, 0, 0},
                {1, 2, 0, 0, 3},
                {1, 2, 0, 0, 3},
                {1, 1, 0, 3, 3}}));
        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0}}));
    }

    public void init() {

        this.blackBlocks = new ArrayList<>();

        blackBlocks.add(new int[][]{{0, 1}, {0, 2}});
        blackBlocks.add(new int[][]{{0, -1}, {1, -1}});
        blackBlocks.add(new int[][]{{0, 1}, {1, 1}});
        blackBlocks.add(new int[][]{{0, -2}, {0, -1}});
        blackBlocks.add(new int[][]{{0, -1}, {0, 1}});
    }

    public boolean process(int x, int y) {

        ArrayDeque<Location> locations = new ArrayDeque<>();
        int key = -1;

        int origin = board[x][y];

        for (int i = 0; i < 5; i++) {

            for (int[] block : blocks[i]) {
                int nx = x + block[0];
                int ny = y + block[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    break;
                if (board[nx][ny] == 0 || board[nx][ny] != origin)
                    break;

                locations.add(new Location(nx, ny));
            }

            if (locations.size() == 3) {
                key = i;
                break;
            }
            locations.clear();
        }

        if(key == -1) {

            locations.add(new Location(x, y));
            boolean[][] visited = new boolean[N][N];
            visited[x][y] = true;

            while(!locations.isEmpty()) {

                Location location = locations.remove();

                for (int i = 0; i < 4; i++) {
                    int nx = location.x + loX[i];
                    int ny = location.y + loY[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                        continue;
                    if (board[nx][ny] == 0 || board[nx][ny] != origin)
                        continue;

                    visited[nx][ny] = true;
                    locations.add(new Location(nx, ny));
                }
            }
            locations.add(new Location(x, y));
            for(Location location : locations)
                board[location.x][location.y] = -1;
            relocation();
            return false;
        }

        if(isCrashed(x, y, key)) {

            locations.add(new Location(x, y));

            for(Location location : locations)
                board[location.x][location.y] = 0;
            return true;
        }
        return false;
    }

    public boolean isCrashed(int x, int y, int key) {

        int[][] check = blackBlocks.get(key);

        for (int[] arr : check) {

            int nx = x + arr[0];
            int ny = y + arr[1];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                return false;
            if (board[nx][ny] != 0)
                return false;
        }
        return true;
    }

    public void relocation() {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {

                if (board[i][j] == -1) {

                    for (int row = i; row < N; row++) {

                        if (board[row][j] == 0)
                            board[row][j] = -1;
                    }
                    break;
                }
            }
        }
    }

    public int solution(int[][] board) {

        int answer = 0;
        boolean flag = false;
        init();

        this.board = board;
        this.N = board.length;

        while (true) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (this.board[i][j] > 0) {
                        if (process(i, j)) {

                            relocation();
                            answer += 1;
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag)
                    break;
            }

            if (!flag)
                break;
            flag = false;
        }
        return answer;
    }
}

class Location {

    int x;
    int y;
    String key;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
