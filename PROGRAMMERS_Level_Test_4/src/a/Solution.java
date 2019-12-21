package a;

public class Solution {

    int Answer;
    int N;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(15));
        System.out.println(new Solution().solution(24));
        System.out.println(new Solution().solution(41));
        System.out.println(new Solution().solution(2147483647));
    }

    public int solution(int n) {

        this.N = n;
        solve(n, 0);
        return Answer;
    }

    private void solve(int total, int plus) {

        if (Math.pow(3, (long)(plus / 2)) > total)
            return;
        if (total == 3) {
            Answer += (plus == 2 ? 1 : 0);
            return;
        }

        if (total > 3) {

            if (total % 3 == 0 && plus >= 2)
                solve(total / 3, plus - 2);
            solve(total - 1, plus + 1);
        }
    }
}
