package pccp.재귀;

import java.util.ArrayList;
import java.util.List;

public class 모음사전 {
    public static void main(String[] args) {
        int solution = new Solution().solution("I");
        System.out.println(solution);
    }

    private static class Solution {

        static String[] aeiou = new String[]{"A", "E", "I", "O", "U"};

        public int solution(String word) {
            List<String> permutation = permutation("");
            return permutation.indexOf(word);
        }

        private List<String> permutation(String word) {
            List<String> words = new ArrayList<>();
            words.add(word);

            if (word.length() == 5) return words;

            for (String s : aeiou) {
                words.addAll(permutation(word + s));
            }

            return words;
        }
    }
}
