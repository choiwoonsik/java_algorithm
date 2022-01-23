package 브루투포스;

import java.util.Arrays;

public class 양궁대회 {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] solution = sol.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
        System.out.println(Arrays.toString(solution));
        System.out.println("-----\n");
        solution = sol.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3});
        System.out.println(Arrays.toString(solution));
        System.out.println("-----\n");
        solution = sol.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0});
        System.out.println(Arrays.toString(solution));
        System.out.println("-----\n");
    }

    private static class Solution {
        static int[] result;
        static boolean[] visited;
        static int[] lions;
        static int Max = -1;
        static int[] point = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        public int[] solution(int n, int[] info) {
            Max = -1;
            result = new int[11];
            visited = new boolean[11];
            lions = new int[11];

            dfs(0, n, info);
            if (Max == -1) result = new int[]{-1};
            return result;
        }

        private static void dfs(int pos, int remainArrow, int[] info) {
            if (pos == 11 || remainArrow == 0) {
                lions[10] += remainArrow;
                int diff = calc(info);
                if (Max < diff) {
                    Max = diff;
                    result = lions.clone();
                } else if (Max == diff) {
                    for (int i = 10; i >= 0; i--) {
                        if (lions[i] > result[i]) {
                            result = lions.clone();
                            break;
                        } else if (result[i] > lions[i]) {
                            break;
                        }
                    }
                }
                lions[10] -= remainArrow;
                return;
            }

            if (remainArrow > info[pos]) {
                lions[pos] = info[pos] + 1;
                dfs(pos + 1, remainArrow - (info[pos] + 1), info);
                lions[pos] = 0;
            }
            dfs(pos + 1, remainArrow, info);
        }

        private static int calc(int[] apeach) {
            int apeachPoint = 0;
            int lionPoint = 0;

            for (int i = 0; i < 11; i++) {
                if (apeach[i] == 0 && lions[i] == 0) {
                    continue;
                }
                if (apeach[i] >= lions[i]) {
                    apeachPoint += point[i];
                } else
                    lionPoint += point[i];
            }
            return lionPoint - apeachPoint;
        }
    }
}
