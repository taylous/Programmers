import java.util.ArrayDeque;

public class Solution {

    boolean[][][][] visited;
    int[][] board;

    int[] loX = { -1, 0, 1, 0 };
    int[] loY = { 0, 1, 0, -1 };

    int N;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[][]{
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0}
        }));
    }

    public int solution(int[][] board) {

        int answer = Integer.MAX_VALUE;

        this.N = board.length;
        this.board = board;

        ArrayDeque<Drone> arrayDeque = new ArrayDeque<>();
        Drone drone;

        visited = new boolean[N][N][N][N];

        int[][] location, temp;
        boolean direction;
        int time, dir;

        arrayDeque.add(new Drone(new int[][] { {0,0}, {0,1} }, true, 0));
        visited[0][0][0][1] = true;

        while(!arrayDeque.isEmpty()) {

            drone = arrayDeque.remove();
            location = drone.location;
            direction = drone.direction;
            time = drone.time;

            if((location[0][0] == N - 1 && location[0][1] == N - 1) || (location[1][0] == N - 1 && location[1][1] == N - 1)) {

                answer = Math.min(answer, time);
                break;
            }

            for(int i = 0; i < 4; i++) {

                for(int j = 0; j < 2; j++) {

                    temp = new int[2][2];

                    temp[j][0] = location[j][0];
                    temp[j][1] = location[j][1];
                    dir = j == 0 ? 1 : 0;

                    temp[dir][0] = location[j][0] + loX[i];
                    temp[dir][1] = location[j][1] + loY[i];

                    if(inspectArea(location[dir], i))
                        continue;
                    if(checkBoundary(temp) || checkVisited(temp))
                        continue;

                    visited[temp[0][0]][temp[0][1]][temp[1][0]][temp[1][1]] = true;
                    arrayDeque.add(new Drone(temp, !direction, time + 1));
                }
                temp = new int[2][2];

                for(int j = 0; j < 2; j++) {
                    temp[j][0] = location[j][0] + loX[i];
                    temp[j][1] = location[j][1] + loY[i];
                }
                if(checkBoundary(temp) || checkVisited(temp))
                    continue;

                visited[temp[0][0]][temp[0][1]][temp[1][0]][temp[1][1]] = true;
                arrayDeque.add(new Drone(temp, direction, time + 1));
            }
        }
        return answer;
    }

    private boolean checkBoundary(int[][] location) {

        int lx = location[0][0];
        int ly = location[0][1];
        int rx = location[1][0];
        int ry = location[1][1];

        return lx < 0 || lx >= N ||
                ly < 0 || ly >= N ||
                rx < 0 || rx >= N ||
                ry < 0 || ry >= N ||
                board[lx][ly] == 1 ||
                board[rx][ry] == 1;
    }

    private boolean checkVisited(int[][] location) {

        return visited[location[0][0]][location[0][1]][location[1][0]][location[1][1]];
    }

    private boolean inspectArea(int[] location, int d) {

        int nx = location[0] + loX[d];
        int ny = location[1] + loY[d];

        return nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] != 0;
    }
}

class Drone {

    int[][] location;
    boolean direction;
    int time;

    public Drone(int[][] location, boolean direction, int time) {
        this.location = location;
        this.direction = direction;
        this.time = time;
    }
}
