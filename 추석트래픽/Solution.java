package KAKAO2018BLINDRECRUITMENT.추석트래픽;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {

    private ArrayList<Traffic> traffics = new ArrayList<>();

    public static void main(String[] args) throws ParseException {
        System.out.println(new Solution().solution(new String[]{"2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"}));
        System.out.println(new Solution().solution(new String[]{"2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"}));
        System.out.println(new Solution().solution(new String[]{"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"}));
    }

    private void init(String[] lines) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        StringTokenizer st;
        String[] times;

        for (String line : lines) {

            st = new StringTokenizer(line);

            String ymd = st.nextToken();
            String time = st.nextToken();
            String diff = st.nextToken().replace("s", "");
            int offset = (int) (Double.parseDouble(diff) * 1000);

            times = time.split("\\.");
            int remain = Integer.parseInt(times[1]);

            Date date = simpleDateFormat.parse(ymd + " " + times[0]);

            long endTime = date.getTime() + remain;
            long startTime = date.getTime() - offset + remain + 1;

            traffics.add(new Traffic(startTime, endTime));
        }
    }

    public int getResult(int n, long criteria, long until) {

        int ret = 0;

        for (int i = 0; i < n; i++) {

            Traffic comp = traffics.get(i);

            if(criteria > comp.startTime) {

                if(comp.endTime >= criteria)
                    ret++;
            }
            else {

                if(comp.startTime < until)
                    ret++;
            }
        }
        return ret;
    }

    public int solution(String[] lines) throws ParseException {

        init(lines);

        long until;
        int n = lines.length;
        int answer = 0;

        for(int idx = 0; idx < n; idx++) {

            Traffic traffic = traffics.get(idx);
            until = traffic.startTime + 1000;

            answer = Math.max(answer, getResult(n, traffic.startTime, until));

            until = traffic.endTime + 1000;
            answer = Math.max(answer, getResult(n, traffic.endTime, until));
        }
        return answer;
    }
}

class Traffic {

    long startTime;
    long endTime;

    public Traffic(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}