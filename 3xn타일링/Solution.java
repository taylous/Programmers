package Problems.tiling;

public class Solution {

    private final int MOD = 1000000007;

    public static void main(String[] args) {

        for(int i = 1; i <= 11; i++)
            System.out.println(new Solution().solution(i));
    }

    public int solution(int n) {

        long[] cache = new long[5001];
        cache[0] = 1;
        cache[2] = 3;

        for(int i = 4; i <= n; i += 2) {
            cache[i] = cache[i - 2] * 3;

            for(int j = i - 4; j >= 0; j -= 2)
                cache[i] += cache[j] * 2;
            cache[i] %= MOD;
        }
        return (int) cache[n];
    }
}
