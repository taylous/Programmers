package Problems.기둥과보설치;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    private int N;

    public static void main(String[] args) {
        int[][] answer = new Solution().solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}});

        for (int[] result : answer)
            System.out.println(Arrays.toString(result));
        System.out.println();

        answer = new Solution().solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});

        for (int[] result : answer)
            System.out.println(Arrays.toString(result));
        System.out.println();

        answer = new Solution().solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}});

        for (int[] result : answer)
            System.out.println(Arrays.toString(result));
        System.out.println();
    }

    public void init(Frame[][] bluePrint, int n) {

        this.N = n;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                bluePrint[i][j] = new Frame(i, j, false, false);
        }
    }

    public boolean check(int x, int y) {

        return (x <= N && x >= 0 && y <= N && y >= 0);
    }

    public boolean inspectColumn(Frame[][] bluePrint, int x, int y) {

        if (x == 0)
            return true;
        if (check(x - 1, y) && bluePrint[x - 1][y].column)
            return true;
        if (y - 1 >= 0 && bluePrint[x][y - 1].beam)
            return true;
        return bluePrint[x][y].beam;
    }

    public boolean inspectBeam(Frame[][] bluePrint, int x, int y) {

        if (check(x, y - 1) && bluePrint[x][y - 1].beam && check(x, y + 1) && bluePrint[x][y + 1].beam)
            return true;
        if (x - 1 >= 0 && bluePrint[x - 1][y].column)
            return true;
        if(x - 1 >= 0 && y + 1 <= N && bluePrint[x - 1][y + 1].column)
            return true;
        return false;
    }

    public int[][] solution(int n, int[][] build_frame) {

        Frame[][] bluePrint = new Frame[n + 1][n + 1];
        ArrayList<Frame> orderList = new ArrayList<>();

        init(bluePrint, n);

        for (int[] order : build_frame) {

            int y = order[0];
            int x = order[1];

            // 설치
            if (order[3] == 1) {

                // 기둥
                if (order[2] == 0) {

                    if (inspectColumn(bluePrint, x, y))
                        bluePrint[x][y].column = true;
                }
                // 보
                else {
                    if (inspectBeam(bluePrint, x, y))
                        bluePrint[x][y].beam = true;
                }
            }
            // 삭제
            else {

                // 기둥
                if (order[2] == 0) {

                    bluePrint[x][y].column = false;
                    if(x + 1 <= N && bluePrint[x + 1][y].column && !inspectColumn(bluePrint, x + 1, y))
                        bluePrint[x][y].column = true;
                    if(x + 1 <= N && y - 1 >= 0 && bluePrint[x + 1][y - 1].beam && !inspectBeam(bluePrint, x + 1, y - 1))
                        bluePrint[x][y].column = true;
                    if(x + 1 <= N && bluePrint[x + 1][y].beam && !inspectBeam(bluePrint, x + 1, y))
                        bluePrint[x][y].column = true;
                }
                // 보
                else {
                    bluePrint[x][y].beam = false;
                    if(y - 1 >= 0 && bluePrint[x][y - 1].beam && !inspectBeam(bluePrint, x, y - 1))
                        bluePrint[x][y].beam  = true;
                    if(y + 1 <= N && bluePrint[x][y + 1].beam && !inspectBeam(bluePrint, x, y + 1))
                        bluePrint[x][y].beam = true;
                    if(y - 1 >= 0 && bluePrint[x][y].column && !inspectColumn(bluePrint, x, y))
                        bluePrint[x][y].beam = true;
                    if(y - 1 >= 0 && bluePrint[x][y + 1].column && !inspectColumn(bluePrint, x, y + 1))
                        bluePrint[x][y].beam = true;
                }
            }
        }

        System.out.println("출력");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {

                if (bluePrint[i][j].beam && bluePrint[i][j].column) {
                    orderList.add(new Frame(i, j, true, false));
                    orderList.add(new Frame(i, j, false, true));
                    System.out.print("┗");
                } else if (bluePrint[i][j].column) {
                    System.out.print("┃");
                    orderList.add(new Frame(i, j, true, false));
                } else if (bluePrint[i][j].beam) {
                    System.out.print("‾");
                    orderList.add(new Frame(i, j, false, true));
                } else
                    System.out.print(" ");
            }
            System.out.println();
        }
        int[][] answer = new int[orderList.size()][3];
        int idx = 0;
        Collections.sort(orderList);

        for (Frame frame : orderList) {
            answer[idx][0] = frame.y;
            answer[idx][1] = frame.x;
            answer[idx++][2] = frame.column ? 0 : 1;
        }
        return answer;
    }
}

class Frame implements Comparable<Frame> {

    int x;
    int y;
    boolean column;
    boolean beam;

    public Frame(int x, int y, boolean column, boolean beam) {
        this.x = x;
        this.y = y;
        this.column = column;
        this.beam = beam;
    }

    @Override
    public int compareTo(Frame other) {
        if (this.y == other.y)
            return this.x - other.x;
        return this.y - other.y;
    }
}