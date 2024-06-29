package pccp.문자열;

import java.util.Arrays;

public class 자연수뒤집어배열로만들기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(1);
    }

    private static class Solution {
        public int[] solution(long n) {
            StringBuilder sb = new StringBuilder(String.valueOf(n));
            return Arrays.stream(sb.reverse().toString().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
