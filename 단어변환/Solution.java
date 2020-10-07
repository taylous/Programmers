package Problems.단어변환;

public class Solution {

    private String[] words;
    private String target;
    private int answer;

    public static void main(String[] args) {
        System.out.println(new Solution().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(new Solution().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
        System.out.println(new Solution().solution("cog", "cog", new String[]{"hot", "dot", "dog", "lot", "log"}));
    }

    public boolean compare(String src, String dest) {

        int count = 0;
        int n = src.length();

        for (int i = 0; i < n && count < 2; i++) {
            if (src.charAt(i) != dest.charAt(i))
                count += 1;
        }
        return count <= 1;
    }

    public void dfs(boolean[] visited, String begin, int step) {

        if (begin.equals(target)) {

            answer = Math.min(answer, step);
            return;
        }

        for (int i = 0; i < words.length; i++) {

            if (!visited[i] && compare(begin, words[i])) {

                visited[i] = true;
                dfs(visited, words[i], step + 1);
                visited[i] = false;
            }
        }
    }

    public int solution(String begin, String target, String[] words) {

        this.words = words;
        this.target = target;
        answer = 987654321;

        dfs(new boolean[words.length], begin, 0);
        return answer == 987654321 ? 0 : answer;
    }
}
