package Problems.네트워크;

import java.util.ArrayDeque;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(new Solution().solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    public void search(int[][] computers, boolean[] visited, int start, int n) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        visited[start] = true;
        deque.add(start);

        while (!deque.isEmpty()) {

            int from = deque.remove();

            for (int to = 0; to < n; to++) {

                if (computers[from][to] > 0 && !visited[to]) {

                    deque.add(to);
                    visited[to] = true;
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (!visited[i]) {

                search(computers, visited, i, n);
                answer += 1;
            }
        }
        return answer;
    }
}
