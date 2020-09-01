package KAKAOBLINDRECRUIMENT.징검다리건너기;

import java.util.Random;

public class Solution {

    private final int INF = 987654321;

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        System.out.println(new Solution().solution(new int[]{1, 1, 1, 3, 2, 1, 4, 2, 5, 1}, 3));
        System.out.println(new Solution().solution(new int[]{1, 2, 1, 3, 2, 1, 4, 2, 1, 1}, 3));
        System.out.println(new Solution().solution(new int[]{1, 1}, 3));

        Random random = new Random();
        int[] stones = new int[200000];

        for(int i = 0; i < 200000; i++)
            stones[i] = random.nextInt(200000000) + 1;
        System.out.println(new Solution().solution(stones, random.nextInt(200000000) + 1));
    }

    public boolean checkBoundary(int[] stones, int k, int offset) {

        int zero = 0;

        for(int stone : stones) {

            if(stone - offset < 0) {
                zero += 1;

                if(zero == k)
                    return false;
                continue;
            }
            zero = 0;
        }
        return true;
    }

    public int binarySearch(int[] stones, int k, int min, int max) {

        if(min == max)
            return min;
        int lo = min, hi = max;

        while(lo < hi) {

            int mid = lo + (hi - lo) / 2;

            if(checkBoundary(stones, k, mid))
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo - 1;
    }

    public int solution(int[] stones, int k) {

        int min = INF;
        int max = -INF;

        for(int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }
        return binarySearch(stones, k, min, max);
    }
}
