package pccp.문자열;

public class 문자열압축 {
    public static void main(String[] args) {
        int solution = new Solution().solution("xababcdcdababcdcd");
        System.out.println(solution);
    }

    private static class Solution {

        static int N;

        public int solution(String origin) {
            N = origin.length();
            int min = origin.length();
            String answer = origin;

            for (int size = 1; size <= N; size++) {
                String zipped = zip(0, origin, size).toString();
                if (zipped.length() < min) {
                    min = zipped.length();
                    answer = zipped;
                    System.out.println(answer);
                }
            }

            return answer.length();
        }

        private StringBuilder zip(int depth, String now, int size) {
            if (size >= now.length()) return new StringBuilder(now);

            StringBuilder sb = new StringBuilder();
            int start = 0;
            int count = 0;
            String prefix = now.substring(start, size);

            while (now.substring(start, start + size).equals(prefix)) {
                count++;
                start += size;
                if (start + size > now.length()) break;
            }

            if (count == 1) {
                return sb
                        .append(prefix)
                        .append(zip(depth + 1, now.substring(start), size));
            } else {
                return sb
                        .append(count)
                        .append(prefix)
                        .append(zip(depth + 1, now.substring(start), size));
            }
        }
    }
}
