package 문자열;

import java.util.*;

public class 신고결과받기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] solution = sol.solution(
                new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}
                , 2);
        System.out.println(Arrays.toString(solution));
    }

    private static class Solution {
        static HashMap<String, Integer> reportCount = new HashMap<>();
        static HashSet<String> reported = new HashSet<>();
        static HashMap<String, ArrayList<String>> reportedBy = new HashMap<>();
        static HashMap<String, Integer> mailCount = new HashMap<>();


        public int[] solution(String[] id_list, String[] report, int k) {
            for (String name : id_list) {
                reportCount.put(name, 0);
                mailCount.put(name, 0);
            }

            for (String rpt : report) {
                if (!reported.contains(rpt)) {
                    reported.add(rpt);
                    StringTokenizer st = new StringTokenizer(rpt);
                    String from = st.nextToken();
                    String to = st.nextToken();
                    reportCount.put(to, reportCount.get(to) + 1);
                    if (!reportedBy.containsKey(to)) {
                        ArrayList<String> reporters = new ArrayList<>();
                        reporters.add(from);
                        reportedBy.put(to, reporters);
                    } else reportedBy.get(to).add(from);
                }
            }

            for (String name : id_list) {
                if (reportCount.get(name) >= k) {
                    for (String reporters : reportedBy.get(name)) {
                        mailCount.put(reporters, mailCount.get(reporters) + 1);
                    }
                }
            }

            int[] result = new int[id_list.length];
            for (int i = 0; i < id_list.length; i++) {
                result[i] = mailCount.get(id_list[i]);
            }
            return result;
        }
    }
}
