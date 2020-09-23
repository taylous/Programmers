package Problems.단속카메라;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {

        System.out.println(new Solution().solution(new int[][]{{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }

    public int solution(int[][] routes) {

        ArrayList<Car> cars = new ArrayList<>();
        int cameraCount = 0;

        for(int[] route : routes)
            cars.add(new Car(route[0] + 30000, route[1] + 30000));
        Collections.sort(cars);

        int offset = -1;

        for(Car car : cars) {

            if(offset < car.enterTime) {

                cameraCount += 1;
                offset = car.outTime;
            }
            offset = Math.min(offset, car.outTime);
        }
        return cameraCount;
    }
}

class Car implements Comparable<Car> {

    int enterTime;
    int outTime;

    public Car(int enterTime, int outTime) {
        this.enterTime = enterTime;
        this.outTime = outTime;
    }

    @Override
    public int compareTo(Car other) {
        if(this.enterTime == other.enterTime)
            return this.outTime < other.outTime ? 1 : -1;
        return this.enterTime - other.enterTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "enterTime=" + enterTime +
                ", outTime=" + outTime +
                '}';
    }
}