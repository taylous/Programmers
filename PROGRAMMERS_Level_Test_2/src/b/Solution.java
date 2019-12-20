package b;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[]{70, 50, 80, 50},100));
    }

    public int solution(int[] people, int limit) {

        int answer = 0;
        int n = people.length;
        int start = 0, end = n - 1;

        Arrays.sort(people);

        for(int i = end; i >= 0; i--) {

            if(start >= i) {
                if(start == i)
                    answer++;
                break;
            }

            if(people[i] == limit) {

                answer++;
                continue;
            }

            if(people[i] + people[start] > limit) {

                answer++;
            }
            else {

                answer++;
                start++;
            }
        }
        return answer;
    }
}
