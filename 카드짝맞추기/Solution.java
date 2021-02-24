package Problems.카드짝맞추기;

import java.util.PriorityQueue;


public class Solution {

    static class Location implements Comparable<Location> {

        int x, y, c;

        public Location(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Location other) {
            return this.c <= other.c ? -1 : 1;
        }
    }

    static class Card {

        int[][] positions;

        public Card() {
            this.positions = new int[2][2];
        }
    }

    private Card[] cards;
    private final int[] loX = {-1, 0, 1, 0};
    private final int[] loY = {0, 1, 0, -1};

    private int Answer;

    public int solution(int[][] board, int r, int c) {

        cards = new Card[7];
        int cardCount = 0;
        Answer = Integer.MAX_VALUE;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {

                int number = board[row][col];

                if (number > 0) {

                    if (cards[number] == null) {
                        cards[number] = new Card();
                        cards[number].positions[0][0] = row;
                        cards[number].positions[0][1] = col;

                        cardCount += 1;
                    } else {
                        cards[number].positions[1][0] = row;
                        cards[number].positions[1][1] = col;
                    }
                }
            }
        }
        search(board, r, c, cardCount, 0);
        return Answer;
    }

    private boolean check(int nx, int ny) {
        return nx < 0 || nx >= 4 || ny < 0 || ny >= 4;
    }

    private int[][] simulation(int[][] board, int cardX, int cardY) {

        PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
        boolean[][][] visited = new boolean[4][4][5];
        int[][] ret = new int[2][2];

        priorityQueue.offer(new Location(cardX, cardY, 0));
        visited[cardX][cardY][0] = true;

        int[][] positions = cards[board[cardX][cardY]].positions;
        int pairX = positions[0][0] == cardX ? positions[1][0] : positions[0][0];
        int pairY = positions[0][1] == cardY ? positions[1][1] : positions[0][1];

        while (!priorityQueue.isEmpty()) {

            Location location = priorityQueue.poll();
            int x = location.x;
            int y = location.y;
            int c = location.c;

            if (x == pairX && y == pairY) {
                ret[0][0] = c + 1;
                ret[1][0] = pairX;
                ret[1][1] = pairY;
                break;
            }
            c += 1;

            for (int i = 0; i < 4; i++) {

                int nx = x;
                int ny = y;
                int offset = 0;

                while (true) {
                    nx += loX[i];
                    ny += loY[i];

                    if (check(nx, ny) || visited[nx][ny][i + 1])
                        break;

                    visited[nx][ny][i + 1] = true;
                    if (board[nx][ny] > 0 || check(nx + loX[i], ny + loY[i])) {

                        priorityQueue.offer(new Location(nx, ny, c));
                        break;
                    }
                    priorityQueue.offer(new Location(nx, ny, c + (offset++)));
                }
            }
        }
        return ret;
    }

    private void search(int[][] board, int cursorX, int cursorY, int cardCount, int move) {

        if (Answer <= move)
            return;
        if (cardCount == 0) {
            Answer = move;
            return;
        }
        PriorityQueue<Location> priorityQueue = new PriorityQueue<>();
        boolean[][] visited = new boolean[4][4];

        priorityQueue.offer(new Location(cursorX, cursorY, 0));
        visited[cursorX][cursorY] = true;

        while (!priorityQueue.isEmpty()) {

            Location location = priorityQueue.poll();
            int x = location.x;
            int y = location.y;
            int c = location.c;

            if (board[x][y] > 0) {

                int[][] ret = simulation(board, x, y);
                int result = ret[0][0];

                if (result > 0) {

                    int[][] copy = new int[4][4];
                    for (int k = 0; k < 4; k++)
                        System.arraycopy(board[k], 0, copy[k], 0, 4);

                    copy[x][y] = 0;
                    copy[ret[1][0]][ret[1][1]] = 0;
                    search(copy, ret[1][0], ret[1][1], cardCount - 1, move + c + result + 1);
                }
            }
            c += 1;

            for (int i = 0; i < 4; i++) {

                int nx = x;
                int ny = y;
                int offset = 0;

                while (true) {
                    nx += loX[i];
                    ny += loY[i];

                    if (check(nx, ny) || visited[nx][ny])
                        break;

                    visited[nx][ny] = true;
                    if (board[nx][ny] > 0 || check(nx + loX[i], ny + loY[i])) {

                        priorityQueue.offer(new Location(nx, ny, c));
                        break;
                    }
                    priorityQueue.offer(new Location(nx, ny, c + (offset++)));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][]{{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0));     // 14
        System.out.println(new Solution().solution(new int[][]{{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}, 0, 1));     // 16
        System.out.println(new Solution().solution(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}, {0, 0, 2, 1}}, 0, 0));     // 10
        System.out.println(new Solution().solution(new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {2, 0, 0, 0}, {2, 0, 1, 0}}, 0, 2));     // 9
        System.out.println(new Solution().solution(new int[][]{{1, 2, 0, 0}, {2, 0, 0, 0}, {3, 0, 0, 0}, {3, 0, 0, 1}}, 0, 0));     //
        System.out.println(new Solution().solution(new int[][]{{1, 2, 4, 0}, {2, 0, 4, 0}, {3, 0, 0, 0}, {3, 0, 0, 1}}, 0, 0));
        System.out.println(new Solution().solution(new int[][]{{1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, 2}}, 2, 2));
        System.out.println(new Solution().solution(new int[][]{{1, 1, 2, 2}, {3, 3, 4, 4}, {5, 5, 6, 6}, {0, 0, 0, 0}}, 3, 3));
        System.out.println(new Solution().solution(new int[][]{{1, 2, 2, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}, 0, 3));
    }
}
