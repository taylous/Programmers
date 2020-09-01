package KAKAOBLINDRECRUIMENT.호텔방배정;

import java.util.HashMap;
import java.util.Random;

public class Solution {

    private HashMap<Long, Long> map;

    public static void main(String[] args) {

        long[] answer = new Solution().solution(10, new long[]{1,3,4,1,3,1});
        print(answer);

        answer = new Solution().solution(10, new long[]{2, 3, 4, 1, 1});
        print(answer);

        Random random = new Random();

        long[] room_number = new long[10];
        int k = 10;

        for(int i = 0; i < room_number.length; i++)
            room_number[i] = random.nextInt(k) + 1;

        System.out.println("before");
        print(room_number);
        System.out.println();

        answer = new Solution().solution(k, room_number);

        System.out.println("after");
        print(answer);
    }

    public static void print(long[] arr) {

        for(long value : arr)
            System.out.print(value + " ");
        System.out.println();
    }

    public long getRoomNumber(long roomNumber) {

        if(!map.containsKey(roomNumber)) {

            map.put(roomNumber, roomNumber + 1);
            return roomNumber;
        }
        long room = getRoomNumber(map.get(roomNumber));
        map.put(roomNumber, room);
        return room;
    }

    public long[] solution(long k, long[] room_number) {

        long[] answer = new long[room_number.length];
        int n = room_number.length;
        map = new HashMap<>();

        for(int i = 0; i < n; i++)
            answer[i] = getRoomNumber(room_number[i]);
        return answer;
    }
}
