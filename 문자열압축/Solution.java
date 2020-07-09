public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().solution("aabbaccc"));
        System.out.println(new Solution().solution("ababcdcdababcdcd"));
        System.out.println(new Solution().solution("abcabcdede"));
        System.out.println(new Solution().solution("abcabcabcabcdededededede"));
        System.out.println(new Solution().solution("xababcdcdababcdcd"));
        System.out.println(new Solution().solution("aaaaaaaaaabcbd"));
		// hidden test case
        System.out.println(new Solution().solution("a"));
    }

    public int solution(String s) {
		
		// exception case
        if(s.length() == 1)
            return 1;
		
        int answer = Integer.MAX_VALUE;
        int start, temp;
		
		// 현재 문자열을 반반씩 비교했을 때가 나눌 수 있는 최대의 수이므로
		// 그 이상은 하지 않습니다.
        for(int offset = 1; offset <= s.length() / 2; offset++) {

            temp = start = 0;

            while(true) {
				
				// 범위검사
                if(start + offset >= s.length()) {

                    temp += s.length() - start;
                    break;
                }
                String partial = s.substring(start, start + offset);
                int[] ret = calculate(s, partial, start + offset);

                temp += ret[0];
                start = ret[1];

                if(start >= s.length())
                    break;
            }
            answer = Math.min(answer, temp);
        }
        return answer;
    }

    public int[] calculate(String origin, String partial, int start) {

        int[] ret = new int[2];
        int offset = partial.length();		// .length()를 계속 호출하는 건 낭비이므로 변수에 저장
        int i = start, count = 0;
        int n = origin.length();			// 위와 같은 이유

        if(i + offset <= origin.length()) {
            for (; i < origin.length(); i += offset) {

                if (i + offset <= n && partial.equals(origin.substring(i, i + offset))) {

                    count++;
                    continue;
                }
                break;		// 위의 if 문에 들어가지 못한다면 더 이상할 필요가 없으므로 break 실행
            }
        }
        ret[0] = count == 0 ? offset : String.valueOf(count + offset).length() + offset;
        ret[1] = i;
        return ret;
    }
}
