
public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().solution(2));
        System.out.println(new Solution().solution(3));
    }

    public int solution(int n) {

        int[] cache = new int[15];

        cache[0] = 1;
        cache[1] = 1;

        for(int i = 2; i <= 14; i++) {
            for(int j = 1; j <= i; j++)
                cache[i] += cache[i - j] * cache[j - 1];
        }
        return cache[n];
    }
}
