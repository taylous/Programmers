package Problems.방문길이;

import java.util.HashSet;

public class Solution {

    private int[] loX = { -1, 0, 1, 0 };
    private int[] loY = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        System.out.println(new Solution().solution("ULURRDLLU"));
        System.out.println(new Solution().solution("LULLLLLLU"));
    }

    public int convertDir(char dir) {

        if(dir == 'U')
            return 0;
        else if(dir == 'R')
            return 1;
        else if(dir == 'D')
            return 2;
        return 3;
    }

    public String getPath(int x, int y, int nx, int ny) {

        StringBuffer sb = new StringBuffer();
        sb.append("(").append(x).append(",").append(y).append(")-");
        sb.append("(").append(nx).append(",").append(ny).append(")");
        return sb.toString();
    }

    public int solution(String dirs) {

        HashSet<String> set = new HashSet<>();
        int answer = 0;

        int x, y;
        x = y = 0;

        for(char dir : dirs.toCharArray()) {

            int d = convertDir(dir);

            int nx = x + loX[d];
            int ny = y + loY[d];

            if(nx < -5 || nx > 5 || ny < -5 || ny > 5)
                continue;

            String path = getPath(x, y, nx, ny);
            answer += set.contains(path) ? 0 : 1;
            set.add(path);
            set.add(getPath(nx, ny, x, y));

            x = nx;
            y = ny;
        }
        return answer;
    }
}
