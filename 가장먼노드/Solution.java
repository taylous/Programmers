package Problems.가장먼노드;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    public int solution(int n, int[][] edge) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<ArrayList<Vertex>> adjacent = new ArrayList<>();
        int[] distance = new int[n + 1];

        int infinity = 987654321;
        int maxValue = 0, answer = 0;

        Arrays.fill(distance, infinity);
        deque.add(1);
        distance[1] = 0;

        for(int i = 0; i <= n; i++)
            adjacent.add(new ArrayList<>());

        for(int[] temp : edge) {
            adjacent.get(temp[0]).add(new Vertex(temp[1], 1));
            adjacent.get(temp[1]).add(new Vertex(temp[0], 1));
        }

        while(!deque.isEmpty()) {

            int from = deque.remove();

            for(Vertex vertex : adjacent.get(from)) {

                if(distance[vertex.no] > distance[from] + vertex.weight) {

                    distance[vertex.no] = distance[from] + vertex.weight;
                    deque.add(vertex.no);
                }
            }
        }

        for(int i = 1; i <= n; i++) {

            if(maxValue < distance[i]) {
                maxValue = distance[i];
                answer = 1;
            }
            else if(maxValue == distance[i]) {
                answer += 1;
            }
        }
        return answer;
    }
}

class Vertex {

    int no;
    int weight;

    public Vertex(int no, int weight) {
        this.no = no;
        this.weight = weight;
    }
}