package Problems.징검다리;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(25, new int[]{2, 14, 11, 21, 17}, 2));
    }

    public int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks);

        int answer = 0;
        int left = 1, right = distance;
        int mid, prev, count;

        while (left <= right) {

            mid = (left + right) / 2;
            prev = count = 0;

            System.out.println("mid: " + mid);

            for (int rock : rocks) {

                if (rock - prev < mid)
                    count += 1;
                else
                    prev = rock;
            }
            System.out.println(count);
            count += (distance - prev < mid ? 1 : 0);

            System.out.println("\tcount: " + count);

            if (count <= n) {

                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
}
