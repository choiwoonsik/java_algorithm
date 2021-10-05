package Gold이상문제_정리;

import java.util.Arrays;

public class 카카오_양궁_4번_2021 {
    public static void main(String[] args) {

        Solution solution = new Solution();
        print(solution.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}), new int[]{0,2,2,0,1,0,0,0,0,0,0});
        print(solution.solution(1, new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new int[]{-1});
        print(solution.solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1}), new int[]{1,1,2,0,1,2,2,0,0,0,0});
        print(solution.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}), new int[]{1,1,1,1,1,1,1,1,0,0,2});
    }

    private static void print(int[] ret, int[] ans) {
        boolean flag = true;

        for (int i = 0; i < ans.length; i++) {
            if (ret[i] != ans[i]) flag = false;
            System.out.print(ret[i]+" ");
        }
        System.out.println("> " + (flag ? "OK !" : "NO..."));
    }

    private static class Solution {
        static int max;
        static int[] answer;
        public int[] solution(int n, int[] info) {
            max = -1;
            answer = new int[11];
            boolean[] visited = new boolean[11];
            int[] myPoint = new int[11];

            shoot(0, n, info, myPoint, visited);
            if (max == -1) {
                return new int[]{-1};
            } else return answer;
        }

        private void shoot(int idx, int n, int[] peachPoint, int[] myPoint, boolean[] visited) {
            // 화살을 다 쓴 경우
            if (n == 0) {

                int peach = 0;
                int my = 0;

                for (int i = 0; i < 11; i++) {
                    if (peachPoint[i] > myPoint[i]) peach += 10 - i;
                    else if (myPoint[i] > peachPoint[i]) my += 10 - i;
                }

                if (my > peach && my >= max) {
                    max = my;
                    answer = myPoint.clone();
                }
                return;
            }

            for (int i = idx; i < 11; i++) {
                if (!visited[i]) {
                    visited[i] = true;

                    // 더 많이 갖고 있으면 쏴봄
                    if (n > peachPoint[i]) {
                        myPoint[i] = peachPoint[i] + 1;
                        shoot(idx + 1, n - (peachPoint[i] + 1), peachPoint, myPoint, visited);
                        myPoint[i] = 0;
                    } else if (i == 10) { // 더 적지만 마지막 이면
                        myPoint[i] = n;
                        shoot(idx + 1, 0, peachPoint, myPoint, visited);
                        myPoint[i] = 0;
                    } else {
                        shoot(idx + 1, n, peachPoint, myPoint, visited);
                    }

                    visited[i] = false;
                }
            }
        }
    }

}
