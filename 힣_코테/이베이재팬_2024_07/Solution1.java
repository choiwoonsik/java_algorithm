package 힣_코테.이베이재팬_2024_07;

import java.util.Arrays;

public class Solution1 {
    int[] dp = new int[301];
    Order[] orderArray;
    boolean[] visited;

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.solution(5, new int[][]{{10, 60}, {15, 30}, {20, 80}, {30, 40}, {35, 70}, {40, 20}}));
    }

    public int solution(int n, int[][] orders) {
        visited = new boolean[orders.length];
        orderArray = new Order[orders.length];
        dp[0] = 0;

        for (int i = 0; i < orders.length; i++) {
            orderArray[i] = new Order(orders[i][0], orders[i][1]);
        }

        recursive(0, 0, n);

        return Arrays.stream(dp).max().orElse(0);
    }

    private void recursive(int beforeDay, int index, int output) {
        if (index >= orderArray.length) {
            return;
        }

        for (int i = index; i < orderArray.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int day = orderArray[i].day;
                int count = orderArray[i].count;

                if ((day - beforeDay) * output >= count) {
                    dp[day] = Math.max(dp[day], dp[beforeDay] + count);
                    recursive(day, i + 1, output);
                } else {
                    recursive(beforeDay, i + 1, output);
                }

                visited[i] = false;
            }
        }
    }

    private class Order {
        int day;
        int count;

        public Order(int day, int count) {
            this.day = day;
            this.count = count;
        }
    }
}

