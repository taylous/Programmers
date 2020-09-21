package Problems.four단고음;

public class Solution {

    private int Answer;

    public static void main(String[] args) {

        for(int i = 5; i <= 41; i++)
            System.out.println(i + ": " + new Solution().solution(i));
        System.out.println(new Solution().solution(2147483647));
    }

    public void fourTreble(int n, int plus) {

        if(n < 1 || 2 * (Math.log(n) / Math.log(3)) < plus)
            return;
        if(n == 3) {

            Answer += plus == 2 ? 1 : 0;
            return;
        }

        if(n % 3 == 0 && plus >= 2)
            fourTreble(n / 3, plus - 2);
        fourTreble(n - 1, plus + 1);
    }

    public int solution(int n) {

        fourTreble(n - 2, 2);
        return Answer;
    }
}
