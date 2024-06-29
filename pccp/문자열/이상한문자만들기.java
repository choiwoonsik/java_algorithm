package pccp.문자열;

public class 이상한문자만들기 {
    public static void main(String[] args) {
        String solution = new Solution().solution("");
        System.out.println(solution);
    }

    private static class Solution {

        static int diff;

        public String solution(String s) {
            diff = 'a' - 'A';

            int lastBlankCount = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (Character.isSpaceChar(s.charAt(i))) lastBlankCount++;
                else break;
            }

            String[] words = s.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String changed = change(words[i]);
                sb.append(changed);
                if (i == words.length - 1) continue;
                sb.append(" ");
            }

            sb.append(" ".repeat(Math.max(0, lastBlankCount)));

            return sb.toString();
        }

        private String change(String word) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i % 2 == 0) c = toUpper(c);
                else c = toLower(c);
                sb.append(c);
            }

            return sb.toString();
        }

        private char toUpper(char c) {
            return Character.toUpperCase(c);
        }

        private char toLower(char c) {
            return Character.toLowerCase(c);
        }
    }
}
