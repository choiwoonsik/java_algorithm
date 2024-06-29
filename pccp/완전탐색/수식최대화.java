package pccp.완전탐색;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 수식최대화 {
    public static void main(String[] args) {
        long solution = new Solution().solution("100-200*300-500+20");
        System.out.println(solution);
    }

    private static class Solution {
        static List<String> expressesList = new ArrayList<>();
        static List<Character> characters = new ArrayList<>();
        static boolean[] visited = new boolean[3];

        public long solution(String expression) {
            long max = 0L;

            char[] charArray = expression.replaceAll("[0-9]", "").toCharArray();
            for (char c : charArray) {
                if (!characters.contains(c)) characters.add(c);
            }

            comb("");

            for (String express : expressesList) {
                max = Math.max(max, expressionSolve(expression, express, 0));
            }

            return max;
        }

        private long expressionSolve(String expression, String express, int expressIndex) {
            StringTokenizer st = new StringTokenizer(expression, express, true);
            List<String> numberTokens = new ArrayList<>();

            while (st.hasMoreTokens()) numberTokens.add(st.nextToken());

            for (String delim : express.split("")) {
                for (int i = 0; i < numberTokens.size(); i++) {
                    if (numberTokens.get(i).equals(delim)) {
                        long front = Long.parseLong(numberTokens.get(i - 1));
                        long back = Long.parseLong(numberTokens.get(i + 1));
                        String operand = numberTokens.get(i);
                        long ret = calculate(front, back, operand);

                        numberTokens.remove(i - 1);
                        numberTokens.remove(i - 1);
                        numberTokens.remove(i - 1);
                        numberTokens.add(i - 1, String.valueOf(ret));
                        i -= 2;
                    }
                }
            }

            return Math.abs(Long.parseLong(numberTokens.get(0)));
        }

        private long calculate(long front, long back, String operand) {
            switch (operand) {
                case "*":
                    return front * back;
                case "+":
                    return front + back;
                case "-":
                    return front - back;
            }
            return -1;
        }

        private void comb(String word) {
            if (word.length() == characters.size()) {
                expressesList.add(word);
                return;
            }

            for (int i = 0; i < characters.size(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    comb(word + characters.get(i));
                    visited[i] = false;
                }
            }
        }
    }
}
