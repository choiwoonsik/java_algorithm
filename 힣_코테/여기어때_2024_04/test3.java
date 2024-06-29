package 힣_코테.여기어때_2024_04;

import java.util.Arrays;
import java.util.PriorityQueue;

public class test3 {
    public static void main(String[] args) {
        Solution_Test3 solution = new Solution_Test3();
        int N = 1;
        int[] coffee_times = {100, 1, 50, 1, 1};
        System.out.println(Arrays.toString(solution.solution(N, coffee_times)));
    }
}

/**
 * 커피는 번호 순서대로 입장한다.
 * 동시에 완료된 커피는 번호가 낮은걸 우선하여 낸다.
 */
class Solution_Test3 {
    public int[] solution(int N, int[] coffee_times) {
        CoffeeMaker coffeeMaker = new CoffeeMaker(N);
        coffeeMaker.makeCoffee(coffee_times);
        return coffeeMaker.coffeeOrder;
    }

    private static class CoffeeMaker {
        private final int machineCount;
        private int time;
        private final PriorityQueue<Coffee> coffeeQueue;
        private int[] coffeeOrder;

        public CoffeeMaker(int machineCount) {
            this.machineCount = machineCount;
            this.time = 0;
            this.coffeeQueue = new PriorityQueue<>();
        }

        public void makeCoffee(int[] coffee_times) {
            int orderNumber = 0;
            int orderIndex = 0;
            coffeeOrder = new int[coffee_times.length];

            while (true) {
                if (coffeeQueue.size() < machineCount && orderNumber < coffee_times.length) {
                    coffeeQueue.add(new Coffee(orderNumber + 1, coffee_times[orderNumber] + time));
                    orderNumber++;
                } else {
                    time++;
                    PriorityQueue<Integer> temporaryQueue = new PriorityQueue<>();
                    while (!coffeeQueue.isEmpty() && coffeeQueue.peek().endTime == time) {
                        Coffee coffee = coffeeQueue.poll();
                        temporaryQueue.add(coffee.number);
                    }
                    while (!temporaryQueue.isEmpty()) {
                        coffeeOrder[orderIndex++] = temporaryQueue.poll();
                    }

                    if (orderIndex == coffee_times.length) break;
                }
            }
        }
    }

    private static class Coffee implements Comparable<Coffee> {
        int number;
        int endTime;

        public Coffee(int number, int endTime) {
            this.number = number;
            this.endTime = endTime;
        }

        public int compareTo(Coffee o) {
            if (this.endTime == o.endTime) return this.number - o.number;
            else return this.endTime - o.endTime;
        }
    }
}
