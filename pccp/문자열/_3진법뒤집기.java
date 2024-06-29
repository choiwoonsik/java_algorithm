package pccp.문자열;

public class _3진법뒤집기 {
    public static void main(String[] args) {
        int solution = new Solution().solution(125);
        System.out.println(solution);
    }

    private static class Solution {
        public int solution(int n) {
            String threeNum = Integer.toString(n, 3); // 10진수를 3진수로 변환해서 반환
            String reversedThreeNum = new StringBuilder(threeNum).reverse().toString();
            return Integer.parseInt(reversedThreeNum, 3); // 3진수로 읽어서 10진수 정수로 반환
        }
    }
}
