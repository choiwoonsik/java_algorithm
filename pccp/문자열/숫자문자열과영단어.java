package pccp.문자열;

public class 숫자문자열과영단어 {
    public static void main(String[] args) {
        int solution = new Solution().solution("2three45sixseven");
        System.out.println(solution);
    }

    private static class Solution {
        public int solution(String s) {
            return convertStringNumberToInt(s);
        }

        private int convertStringNumberToInt(String s) {
            StringBuilder sb = new StringBuilder();
            char[] words = s.toCharArray();

            for (int i = 0; i < words.length; ) {
                char word = words[i];
                if (Character.isDigit(word)) {
                    i++;
                    sb.append(word);
                } else if (word == 'z') {
                    i += 4;
                    sb.append("0");
                } else if (word == 'o') {
                    i += 3;
                    sb.append("1");
                } else if (word == 't') {
                    if (words[i + 1] == 'w') {
                        i += 3;
                        sb.append("2");
                    } else {
                        i += 5;
                        sb.append("3");
                    }
                } else if (word == 'f') {
                    if (words[i + 1] == 'o') {
                        i += 4;
                        sb.append("4");
                    } else {
                        i += 4;
                        sb.append("5");
                    }
                } else if (word == 's') {
                    if (words[i + 1] == 'i') {
                        i += 3;
                        sb.append("6");
                    } else {
                        i += 5;
                        sb.append("7");
                    }
                } else if (word == 'e') {
                    i += 5;
                    sb.append("8");
                } else if (word == 'n') {
                    i += 4;
                    sb.append("9");
                }
            }

            return Integer.parseInt(sb.toString());
        }
    }
}
