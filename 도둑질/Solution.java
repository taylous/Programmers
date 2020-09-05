package KAKAO.도둑질;

import java.util.Arrays;
import java.util.Random;

public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{1, 2, 3, 1}));
        System.out.println(new Solution().solution(new int[]{5, 2, 3, 9}));
        System.out.println(new Solution().solution(new int[]{1, 5, 5, 10, 9}));
        System.out.println(new Solution().solution(new int[]{5, 2, 3, 20, 9}));
        System.out.println(new Solution().solution(new int[]{10, 0, 0, 2, 9}));

        Random random = new Random();
        int[] money = new int[1000000];

        for(int i = 0; i < 1000000; i++)
            money[i] = random.nextInt(1000);

        System.out.println(new Solution().solution(money));
    }

    public int solution(int[] money) {

        int answer = 0;
        int n = money.length;
        int[] cache = new int[n];

        cache[0] = cache[1] = money[0];
        for(int i = 2; i < n - 1; i++) {
            cache[i] = Math.max(cache[i - 1], money[i] + cache[i - 2]);
            answer = Math.max(answer, cache[i]);
        }

        Arrays.fill(cache, 0);
        cache[0] = 0;
        cache[1] = money[1];

        for(int i = 2; i < n; i++) {
            cache[i] = Math.max(cache[i - 1], money[i] + cache[i - 2]);
            answer = Math.max(answer, cache[i]);
        }
        return answer;
    }
}
