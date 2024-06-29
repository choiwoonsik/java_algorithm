package pccp.완전탐색;

import java.util.*;

public class 소수찾기 {
    public static void main(String[] args) {
        int solution = new Solution().solution("2");
        System.out.println(solution);
    }

    private static class Solution {

        static boolean[] visited;
        static List<String> numPermus = new ArrayList<>();
        static String[] nums;
        static Set<Integer> primeSet = new HashSet<>();
        public int solution(String numbers) {
            nums = numbers.split("");
            visited = new boolean[nums.length];

            permutation("");
            numPermus.remove(0);
            findAllPrime();

            return primeSet.size();
        }

        private void findAllPrime() {
            for (String num : numPermus) {
                int number = Integer.parseInt(num);
                if (isPrime(number)) {
                    primeSet.add(number);
                }
            }
        }

        private boolean isPrime(int number) {
            if (number == 0) return false;
            if (number == 1) return false;

            for (int div = 2; div <= Math.sqrt(number); div++) {
                if (number % div == 0) return false;
            }

            return true;
        }

        private void permutation(String word) {
            numPermus.add(word);

            if (word.length() == nums.length) return;

            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    permutation(word + nums[i]);
                    visited[i] = false;
                }
            }
        }
    }
}
