package Problems.광고삽입;

import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] totalTimes;
        long max, total = 0;
        int location = 0;

        int playTime = convertTimeToInt(play_time);
        int advTime = convertTimeToInt(adv_time);

        totalTimes = new int[playTime + 1];

        for (String log : logs) {
            String[] times = log.split("-");

            int start = convertTimeToInt(times[0]);
            int end = convertTimeToInt(times[1]);

            for (; start < end; start++)
                totalTimes[start] += 1;
        }

        for (int time = 0; time < advTime; time++) {
            total += totalTimes[time];
            deque.add(totalTimes[time]);
        }
        max = total;

        for (int time = advTime; time < playTime; time++) {

            total += totalTimes[time];
            total -= deque.removeFirst();
            deque.add(totalTimes[time]);

            if (max < total) {

                location = time - advTime + 1;
                max = total;
            }
        }
        return convertTimeToStr(location);
    }

    private String convertTimeToStr(int time) {

        String[] strTime = new String[3];

        for (int i = 2; i >= 0; i--) {
            strTime[i] = (time % 60) < 10 ? "0" : "";
            strTime[i] += String.valueOf(time % 60);
            time /= 60;
        }
        return String.join(":", strTime);
    }

    private int convertTimeToInt(String time) {

        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken()) * 60 * 60;
        int minuete = Integer.parseInt(st.nextToken()) * 60;
        int second = Integer.parseInt(st.nextToken());
        return hour + minuete + second;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
        System.out.println(new Solution().solution("99:59:59", "25:00:00", new String[]{"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"}));
        System.out.println(new Solution().solution("50:00:00", "50:00:00", new String[]{"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}));
    }
}
