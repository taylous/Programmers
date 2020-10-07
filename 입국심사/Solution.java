package Problems.입국심사;

import java.io.IOException;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.out.println(new Solution().solution(6, new int[]{7, 10}));   // 28
        System.out.println(new Solution().solution(10, new int[]{3, 8, 3, 6, 9, 2, 4}));    // 8
        System.out.println(new Solution().solution(20, new int[]{45, 46, 59, 50, 30, 23, 11, 6, 60, 74}));  // 55

//        BufferedReader br = new BufferedReader(new FileReader("./src/Problems/입국심사/test.txt"));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int m = Integer.parseInt(st.nextToken());
//        int n = Integer.parseInt(st.nextToken());
//
//        int[] times = new int[m];
//
//        for(int i = 0; i < m; i++)
//            times[i] = Integer.parseInt(br.readLine());
//        System.out.println(new Solution().solution(n, times));
//        br.close();
    }

    public long isFinished(int[] times, long deadline, int n) {

        long done = 0;

        for (int time : times)
            done += deadline / time;
        return done;
    }

    public long solution(int n, int[] times) {

        Arrays.sort(times);

        int len = times.length;
        long answer = Long.MAX_VALUE;
        long left = 0;
        long right = (long) n * (long) times[len - 1];

        while (left <= right) {

            long deadline = left + (right - left) / 2;
            long done = isFinished(times, deadline, n);

            if (done >= n) {

                answer = Math.min(answer, deadline);
                right = deadline - 1;
            } else {
                left = deadline + 1;
            }
        }
        return answer;
    }
}
