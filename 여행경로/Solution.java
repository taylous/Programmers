package Problems.여행경로;

import java.util.*;

public class Solution {

    private ArrayList<String> paths;

    public String[] solution(String[][] tickets) {

        paths = new ArrayList<>();
        int n = tickets.length;
        boolean[] visited = new boolean[n];

        dfs(tickets, visited, new StringBuilder("ICN"), "ICN", 0);
        Collections.sort(paths);
        StringTokenizer st = new StringTokenizer(paths.get(0));

        String[] answer = new String[st.countTokens()];
        int idx = 0;

        while(st.hasMoreTokens())
            answer[idx++] = st.nextToken();
        return answer;
    }

    public void dfs(String[][] tickets, boolean[] visited, StringBuilder path, String from, int ticketCount) {

        if(tickets.length == ticketCount) {

            paths.add(path.toString());
            return;
        }

        for(int i = 0; i < tickets.length; i++) {
            if(tickets[i][0].equals(from) && !visited[i]) {

                visited[i] = true;
                StringBuilder temp = new StringBuilder(path.toString());
                temp.append(" ").append(tickets[i][1]);
                dfs(tickets, visited, temp, tickets[i][1], ticketCount + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new String[][]{
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"}
        })));
        System.out.println(Arrays.toString(new Solution().solution(new String[][]
                {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}}
        )));
        System.out.println(Arrays.toString(new Solution().solution(new String[][]{
                {"ICN", "A"},
                {"A", "C"},
                {"A", "D"},
                {"D", "B"},
                {"B", "A"}
        })));
        System.out.println(Arrays.toString(new Solution().solution(new String[][]{
                {"ICN", "A"},
                {"ICN", "B"},
                {"B", "ICN"}
        })));
        System.out.println(Arrays.toString(new Solution().solution(new String[][]{{"ICN", "COO"}, {"COO", "ICN"}, {"COO", "ICN"}})));
    }
}
