package a;

public class Solution {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(15));
    }

    public int solution(int n) {

        int ret = 0, one = getOne(n);

        for(int i = n + 1; i <= 1000000; i++) {

            if(getOne(i) == one) {

                ret = i;
                break;
            }
        }
        return ret;
    }

    private int getOne(int n) {

        int one = 0;

        while(n > 0) {

            one += n % 2 == 1 ? 1 : 0;
            n /= 2;
        }
        return one;
    }
}
