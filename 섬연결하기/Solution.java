package Problems.섬연결하기;

import java.util.PriorityQueue;

public class Solution {

    private int[] parents;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}));
    }

    public void union(int sV, int eV) {

        int sP = find(sV);
        int eP = find(eV);

        if (sP != eP)
            parents[sP] = eP;
    }

    public int find(int vertex) {

        if (vertex == parents[vertex])
            return vertex;
        return parents[vertex] = find(parents[vertex]);
    }

    public int solution(int n, int[][] costs) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        this.parents = new int[n];
        int answer = 0;

        for (int i = 0; i < n; i++)
            parents[i] = i;

        for (int[] cost : costs)
            pq.offer(new Edge(cost[0], cost[1], cost[2]));

        while (!pq.isEmpty()) {

            Edge edge = pq.poll();
            int sV = edge.startVertex;
            int eV = edge.endVertex;

            int sP = find(sV);
            int eP = find(eV);

            if (sP == eP)
                continue;

            union(sP, eP);
            answer += edge.weight;
        }
        return answer;
    }
}

class Edge implements Comparable<Edge> {

    int startVertex;
    int endVertex;
    int weight;

    public Edge(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}