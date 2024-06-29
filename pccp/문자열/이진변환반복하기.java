package pccp.문자열;

import java.util.Arrays;

public class 이진변환반복하기 {
    public static void main(String[] args) {
        int[] solution = new Solution().solution("110010101001");
        System.out.println(Arrays.toString(solution));
    }

    private static class Solution {
        private int[] solution(String n) {
            return binaryTransform(n, 0, 0);
        }

        private int[] binaryTransform(String number, int depth, int count) {
            if (number.equals("1")) return new int[]{depth, count};

            String onlyOne = number.replaceAll("0", "");
            int zeroCount = number.length() - onlyOne.length();
            int onlyOneLength = onlyOne.length();
            String binary = Integer.toString(onlyOneLength, 2);

            return binaryTransform(binary, depth + 1, count + zeroCount);
        }
    }
}
