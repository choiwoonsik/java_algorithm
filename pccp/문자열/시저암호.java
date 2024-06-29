package pccp.문자열;

public class 시저암호 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String solution1 = solution.solution("", 1);
        System.out.println(solution1);
    }
    private static class Solution {
        public String solution(String s, int n) {
            char[] chars = s.toCharArray();

            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (c == ' ') sb.append(c);
                else {
                    if (c >= 'A' && c <= 'Z') {
                        c += n;
                        if (c > 'Z') {
                            int diff = c - 'Z';
                            c = (char) ('A' + diff - 1);
                        }
                    } else if (c >= 'a' && c <= 'z') {
                        c += n;
                        if (c > 'z') {
                            int diff = c - 'z';
                            c = (char) ('a' + diff - 1);
                        }
                    }
                }
                sb.append(c);
            }

            return sb.toString();
        }
    }

}
