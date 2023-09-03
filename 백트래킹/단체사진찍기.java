package 백트래킹;

import java.util.*;

class Solution {
    static String[] LINE = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static HashMap<String, ArrayList<Want>> wantMap = new HashMap<>();
    static int totalCount = 0;
    static int MAX = 8;

    public int solution(int n, String[] data) {
        totalCount = 0;
        MAX = 8;
        wantMap = new HashMap<>();
        makeWantMap(n, data);
        permutation(0, new boolean[8], new String[8]);

        return totalCount;
    }

    public void makeWantMap(int n, String[] data) {
        for (int i = 0; i < n; i++) {
            String who = String.valueOf(data[i].charAt(0));
            String target = String.valueOf(data[i].charAt(2));
            String dir = data[i].charAt(3) == '<' ? "in" : (data[i].charAt(3) == '>' ? "out" : "same");
            int dist = data[i].charAt(4) - '0';

            if (!wantMap.containsKey(who)) wantMap.put(who, new ArrayList<Want>());
            wantMap.get(who).add(new Want(target, dir, dist));
        }
    }

    public void permutation(int len, boolean[] visited, String[] curLine) {
        if (len == MAX) {
            boolean result = checkLine(curLine, MAX);
            if (result) totalCount++;
            return;
        }

        for (int i = 0; i < MAX; i++) {
            if (!visited[i]) {
                visited[i] = true;
                curLine[len] = LINE[i];
                permutation(len + 1, visited, curLine);
                visited[i] = false;
            }
        }
    }

    public boolean checkLine(String[] curLine, int max) {
        for (int i = 0; i < max; i++) {
            String who = curLine[i];

            if (wantMap.containsKey(who)) {
                ArrayList<Want> wants = wantMap.get(who);
                for (Want want : wants) {
                    if (!checkWant(i, curLine, want.target, want.dir, want.dist)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkWant(int curIdx, String[] curLine, String target, String dir, int dist) {
        int targetIdx = indexOf(curLine, target);

        if (dir.equals("in")) {
            return Math.abs(targetIdx - curIdx) - 1 < dist;
        } else if (dir.equals("out")) {
            return Math.abs(targetIdx - curIdx) - 1 > dist;
        } else {
            return Math.abs(targetIdx - curIdx) - 1 == dist;
        }
    }

    public int indexOf(String[] arr, String target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    static public class Want {
        String target;
        String dir;
        int dist;

        public Want(String target, String dir, int dist) {
            this.target = target;
            this.dir = dir;
            this.dist = dist;
        }
    }
}

public class 단체사진찍기 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int solution = sol.solution(2, new String[]{"N~F=0", "R~T>2"});
        int solution2 = sol.solution(2, new String[]{"M~C<2", "C~M>1"});
        System.out.println(solution);
        System.out.println(solution2);
    }
}
