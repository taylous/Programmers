import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().solution(new String[][]{
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        }));
        System.out.println(new Solution().solution(new String[][]{
                {"crow_mask", "face"},
                {"blue_sunglasses", "face"},
                {"smoky_makeup", "face"}
        }));
    }

    public int solution(String[][] clothes) {

        HashMap<String, Integer> map = new HashMap<>();
        int answer = 1;

        for(String[] cloth : clothes)
            map.put(cloth[1], map.getOrDefault(cloth[1], 0) + 1);
        for(int value : map.values())
            answer *= (value + 1);
        return answer - 1;
    }
}
