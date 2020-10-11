package Problems.N으로표현;

public class Solution {

    private int Answer;
    private int Number;
    private int N;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(5, 12));
        System.out.println(new Solution().solution(2, 11));
        System.out.println(new Solution().solution(9, 10000));
    }

    public void solve(int value, int count) {

        if (count > 8)
            return;

        if (value == Number) {

            Answer = Math.min(Answer, count);
            return;
        }

        int offset = N;
        for (int i = 1; i <= 8; i++) {

            solve(value + offset, count + i);
            solve(value - offset, count + i);
            if (value != 0) {
                solve(value * offset, count + i);
                solve(value / offset, count + i);
            }
            offset = (offset * 10) + N;
        }
    }

    public int solution(int N, int number) {

        this.Answer = Integer.MAX_VALUE;
        this.Number = number;
        this.N = N;

        solve(0, 0);
        return Answer == Integer.MAX_VALUE ? -1 : Answer;
    }
}
