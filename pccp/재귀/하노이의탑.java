package pccp.재귀;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 하노이의탑 {
    public static void main(String[] args) {
        int[][] solution = new Solution().solution(2);
        for (int i = 0; i < solution.length; i++) {
            System.out.println(i + ": " + Arrays.toString(solution[i]));
        }
    }

    /*
    n개의 탑을 from -> to로 옮긴다.

    종료조건: 1개의 탑을 from -> to로 옮긴다.
    1. n-1개의 탑을 from -> empty로 옮긴다.
    2. 1개의 탑을 from -> to로 옮긴다.
    3. n-1개의 탑을 empty -> to로 옮긴다.
     */
    private static class Solution {
        public int[][] solution(int n) {
            ArrayList<int[]> process = new ArrayList<>();
            hanoi(n, 1, 3, process);
            return process.toArray(new int[0][]);
        }

        private void hanoi(int n, int from, int to, List<int[]> process) {
            if (n == 1) {
                process.add(new int[]{from, to});
                return;
            }

            int empty = 6 - from - to;

            hanoi(n - 1, from, empty, process);
            hanoi(1, from, to, process);
            hanoi(n - 1, empty, to, process);
        }
    }
}
