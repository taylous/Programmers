public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10}));
        System.out.println(new Solution().solution(new int[]{1, 3, 2, 5, 4}));
        System.out.println(new Solution().solution(new int[]{1}));
        System.out.println(new Solution().solution(new int[]{1,5,1}));
    }

    public int solution(int[] sticker) {

        if(sticker.length == 1)
            return sticker[0];
        int n = sticker.length;

        int[] cache1 = new int[n];
        int[] cache2 = new int[n];

        cache1[0] = cache1[1] = sticker[0];
        cache2[0] = 0;
        cache2[1] = sticker[1];

        for (int i = 2; i < n - 1; i++)
            cache1[i] = Math.max(cache1[i - 1], sticker[i] + cache1[i - 2]);
        for (int i = 2; i < n; i++)
            cache2[i] = Math.max(cache2[i - 1], sticker[i] + cache2[i - 2]);
        return Math.max(cache1[n - 2], cache2[n - 1]);
    }
}
