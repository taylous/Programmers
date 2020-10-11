package Problems.tiling2n;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(4));
        System.out.println(new Solution().solution(5));
        System.out.println(new Solution().solution(60000));
    }

    public int solution(int n) {

        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;

        for(int i = 2; i <= n; i++)
            cache[i] = (cache[i - 1] + cache[i - 2]) % 1000000007;
        return cache[n];
    }
}
