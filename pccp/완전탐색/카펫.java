package pccp.완전탐색;

import java.util.Arrays;

public class 카펫 {
    public static void main(String[] args) {
        int[] solution = new Solution().solution(24, 24);
        System.out.println(Arrays.toString(solution));
    }

    private static class Solution {
        public int[] solution(int brown, int yellow) {
            int sum = brown + yellow;
            int w = 0;
            int h = 0;

            for (int widht = 2; widht < sum / 2; widht++) {
                int height = sum / widht;
                if (sum % widht != 0) continue;

                if (widht >= height) {
                    if (widht * 2 + height * 2 - 4 == brown) {
                        w = widht;
                        h = height;
                        break;
                    }
                }
            }

            return new int[]{w, h};
        }
    }
}
