package pccp.문자열;

public class 신규아이디추천 {
    public static void main(String[] args) {
        String solution = new Solution().solution("...!@BaT#*..y.abcdefghijklm"	);
        System.out.println(solution);
    }

    private static class Solution {
        public String solution(String new_id) {
            String pass = new_id.toLowerCase();
            return recommendNewId(pass);
        }

        private String recommendNewId(String newId) {
            String step1 = newId.replaceAll("^\\.|\\.$", "");
            String step2 = step1.replaceAll("[^a-z0-9._-]", "");
            String step3 = step2.replaceAll("\\.+", ".");
            String step4 = step3.replaceAll("^\\.|\\.$", "");
            if (step4.isEmpty()) step4 = "a";
            if (step4.length() >= 16) step4 = step4.substring(0, 15);
            StringBuilder step5 = new StringBuilder(step4.replaceAll("^\\.|\\.$", ""));
            char last = step5.charAt(step5.length() - 1);
            while (step5.length() <= 2) {
                step5.append(last);
            }

            return step5.toString();
        }
    }
}
