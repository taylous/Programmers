package Problems.외벽점검;

import java.util.ArrayList;

public class Solution {

    private ArrayList<int[]> combinations;
    private int friendsCount;

    public static void main(String[] args) {
        System.out.println(new Solution().solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(new Solution().solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }

    public int solve(ArrayList<Integer> straightWeak, int[] comb, int[] dist, int until) {

        int size = straightWeak.size() / 2;
        int ret = Integer.MAX_VALUE;

        for(int i = 0; i <= size; i++) {

            int start = straightWeak.get(i);

            int usedFriend = 1;
            int friendsIdx = 0;
            int remain = until;

            for(int weakIdx = i; weakIdx <= i + size; weakIdx++) {

                if(start + dist[comb[friendsIdx]] >= straightWeak.get(weakIdx)) {

                    remain -= 1;
                }
                else {

                    start = straightWeak.get(weakIdx);
                    friendsIdx += 1;
                    usedFriend += 1;
                    weakIdx -= 1;

                    if(friendsIdx == comb.length)
                        break;
                }
            }
            if(remain == 0)
                ret = Math.min(ret, usedFriend);
        }
        return ret;
    }

    public void permutation(int[] comb, boolean[] check, int idx, int maxDepth) {

        if(idx == maxDepth) {

            int[] temp = new int[maxDepth];
            System.arraycopy(comb, 0, temp, 0, maxDepth);
            combinations.add(temp);
            return;
        }

        for(int i = 0; i < friendsCount; i++) {

            if(!check[i]) {

                comb[idx] = i;
                check[i] = true;
                permutation(comb, check, idx + 1, maxDepth);
                check[i] = false;
                comb[idx] = 0;
            }
        }
    }

    public int solution(int n, int[] weak, int[] dist) {

        ArrayList<Integer> straightWeak = new ArrayList<>();
        int until = weak.length;
        int answer = Integer.MAX_VALUE;

        for (int value : weak) straightWeak.add(value);
        for (int value : weak) straightWeak.add(value + n);
        straightWeak.remove(straightWeak.size() - 1);

        this.combinations = new ArrayList<>();
        this.friendsCount = dist.length;

        for(int depth = 1; depth <= dist.length; depth++) {
            permutation(new int[this.friendsCount], new boolean[this.friendsCount], 0, depth);

            for (int[] combination : combinations)
                answer = Math.min(answer, solve(straightWeak, combination, dist, until));

            if(answer != Integer.MAX_VALUE)
                break;
            combinations.clear();
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
