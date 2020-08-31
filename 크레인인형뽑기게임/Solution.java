package KAKAOBLINDRECRUIMENT.크레인인형뽑기게임;

import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {

        System.out.println(new Solution().solution(
                new int[][]{
                        {0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}
                        },
                new int[]{1, 5, 3, 5, 1, 2, 1, 4})
        );
    }

    public int removeFromBasket(ArrayList<Integer> basket) {

        int size = basket.size();
        int ret = 0;

        for(int i = 0; i < size - 1; i++) {
            if(basket.get(i).equals(basket.get(i + 1))) {
                basket.remove(i);
                basket.remove(i);
                i -= 2;
                size -= 2;

                ret += 2;
            }
        }
        return ret;
    }

    public int solution(int[][] board, int[] moves) {

        ArrayList<Integer> basket = new ArrayList<>();

        for(int col : moves) {

            for (int[] row : board) {

                if (row[col - 1] > 0) {
                    basket.add(0, row[col - 1]);
                    row[col - 1] = 0;
                    break;
                }
            }
        }
        return removeFromBasket(basket);
    }
}
