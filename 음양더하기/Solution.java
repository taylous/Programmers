package Problems.음양더하기;

public class Solution {

    public int solution(int[] absolutes, boolean[] signs) {

        int size = absolutes.length;
        int answer = 0;

        for (int index = 0; index < size; index++)
            answer += signs[index] ? absolutes[index] : absolutes[index] * -1;
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{4, 7, 12}, new boolean[]{true, false, true}));
        System.out.println(new Solution().solution(new int[]{1, 2, 3}, new boolean[]{false, false, true}));
    }
}
