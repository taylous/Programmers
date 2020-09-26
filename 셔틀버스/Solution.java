package Problems.셔틀버스;

import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(1, 1, 5, new String[]{"08:00", "08:01", "08:02", "08:03"}));
        System.out.println(new Solution().solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
        System.out.println(new Solution().solution(2, 1, 2, new String[]{"09:00", "09:00", "09:00", "09:00"}));
        System.out.println(new Solution().solution(1, 1, 5, new String[]{"00:01", "00:01", "00:01", "00:01", "00:01"}));
        System.out.println(new Solution().solution(1, 1, 1, new String[]{"23:59"}));
        System.out.println(new Solution().solution(10, 60, 45, new String[]{"23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}));
    }

    public String solution(int n, int t, int m, String[] timetable) {

        // n : 셔틀 수
        // t : 몇 분 간격?
        // m : 셔틀에 탈 수 있는 최대수
        LinkedList<Integer> commuters = new LinkedList<>();
        LinkedList<Integer> bus = new LinkedList<>();
        StringTokenizer st;

        int shuttleTime = 540;
        int lastTime = 0;
        int arrivalTime;

        for (String time : timetable) {

            st = new StringTokenizer(time, ":");

            int hour = Integer.parseInt(st.nextToken()) * 60;
            int minute = Integer.parseInt(st.nextToken());

            if (hour + minute == 1439)
                continue;
            commuters.add(hour + minute);
        }
        Collections.sort(commuters);

        while (n > 1 && !commuters.isEmpty()) {

            while (bus.size() < m && !commuters.isEmpty()) {

                if (commuters.peekFirst() > shuttleTime)
                    break;
                arrivalTime = commuters.remove();

                if (shuttleTime >= arrivalTime)
                    bus.add(arrivalTime);
            }
            shuttleTime += t;
            n -= 1;

            bus.clear();
        }

        if (n == 1) {

            while (bus.size() < m && !commuters.isEmpty()) {

                arrivalTime = commuters.remove();

                if (shuttleTime >= arrivalTime)
                    bus.add(arrivalTime);
            }

            if (bus.size() == m) {
                lastTime = bus.removeLast() - 1;
            } else {
                lastTime = shuttleTime;
            }
        } else {

            while (n-- > 1)
                shuttleTime += t;
            lastTime = shuttleTime;
        }
        int hour = lastTime / 60;
        int minute = lastTime % 60;

        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute);
    }
}
