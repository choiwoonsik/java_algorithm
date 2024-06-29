package pccp.완전탐색;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 불량사용자 {
    public static void main(String[] args) {
        int solution = new Solution().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"});
        System.out.println(solution);
    }

    private static class Solution {

        static Set<Set<String>> bannedSet = new HashSet<>();

        public int solution(String[] user_id, String[] banned_id) {
            String[][] allBans = Arrays.stream(banned_id)
                    .map(bannedId -> bannedId.replace('*', '.'))
                    .map(bannedId ->
                            Arrays.stream(user_id)
                                    .filter(userId -> userId.matches(bannedId))
                                    .toArray(String[]::new)
                    ).toArray(String[][]::new);

            combination(0, allBans, new HashSet<>(), bannedSet);

            return bannedSet.size();
        }

        private void combination(int index, String[][] bans, Set<String> banned, Set<Set<String>> bannedSet) {
            if (index == bans.length) {
                bannedSet.add(new HashSet<>(banned));
                return;
            }

            for (String ban : bans[index]) {
                if (!banned.contains(ban)) {
                    banned.add(ban);
                    combination(index + 1, bans, banned, bannedSet);
                    banned.remove(ban);
                }
            }
        }
    }
}
