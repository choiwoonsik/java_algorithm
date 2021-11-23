package Gold이상문제_정리;

import java.util.*;

/*
. Z I .
M . * *
M Z U .
. I U .

F G H E I
B A B . .
D . C * .
C A . . I
D F H G E
*/
public class kakao_리틀프렌즈사천성 {
    public static void main(String[] args) {
        String solution;
        solution = Solution.solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."});
        System.out.println(solution.equals("MUZI") + ", " + solution);
        solution = Solution.solution(2, 4, new String[]{"NRYN", "ARYA"});
        System.out.println(solution.equals("RYAN")+", "+solution);
        solution = Solution.solution(5, 5, new String[]{"FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE"});
        System.out.println(solution.equals("ABCDFHGIE") + ", " + solution);
    }
    private static class Solution {
        static char[][] table;
        static int N, M;
        static StringBuilder answer = new StringBuilder();
        static HashMap<Character, int[][]> map = new HashMap<>();
        static ArrayList<Character> list = new ArrayList<>();

        private static String solution(int m, int n, String[] board) {
            N = m;
            M = n;
            map.clear();
            list.clear();
            answer = new StringBuilder();
            table = new char[N][M];
            for (int i = 0; i < N; i++) {
                String line = board[i];
                for (int j = 0; j < M; j++) {
                    table[i][j] = line.charAt(j);
                    if (table[i][j] != '.' && table[i][j] != '*') {
                        if (!map.containsKey(table[i][j])) {
                            list.add(table[i][j]);
                            map.put(table[i][j], new int[2][2]);
                            map.get(table[i][j])[0][0] = i;
                            map.get(table[i][j])[0][1] = j;
                        } else {
                            map.get(table[i][j])[1][0] = i;
                            map.get(table[i][j])[1][1] = j;
                        }
                    }
                }
            }

            list = new ArrayList<>(map.keySet());
            list.sort(null);

            int idx = 0;
            while (list.size() != 0) {
                if (canMatch(map.get(list.get(idx)), list.get(idx))) {
                    char c = list.remove(idx);
                    answer.append(c);
                    deleteBlock(c);
                    idx = 0;
                } else {
                    idx++;
                    if (idx == list.size())
                        return "IMPOSSIBLE";
                }
            }
            return answer.toString();
        }

        private static void deleteBlock(char c) {
            int[][] pos = map.get(c);
            table[pos[0][0]][pos[0][1]] = '.';
            table[pos[1][0]][pos[1][1]] = '.';
        }

        private static boolean canMatch(int[][] dots, char target) {
            int sy = dots[0][0];
            int sx = dots[0][1];
            int dy = dots[1][0];
            int dx = dots[1][1];

            if (sx < dx) {
                if (rowMatch(sx, sy, dy, target) && colMatch(dy, sx, dx, target)) {
                    return true;
                }
                if (colMatch(sy, sx, dx, target) && rowMatch(dx, sy, dy, target)) {
                    return true;
                }
            } else { // dx <= sx
                if (rowMatch(sx, sy, dy, target) && colMatch(dy, dx, sx, target)) {
                    return true;
                }
                if (colMatch(sy, dx, sx, target) && rowMatch(dx, sy, dy, target)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean colMatch(int fixY, int fromX, int toX, char target) {
            for (int i = fromX; i <= toX; i++) {
                if (table[fixY][i] != '.' && table[fixY][i] != target)
                    return false;
            }
            return true;
        }

        private static boolean rowMatch(int fixX, int fromY, int toY, char target) {
            for (int i = fromY; i <= toY; i++) {
                if (table[i][fixX] != '.' && table[i][fixX] != target)
                    return false;
            }
            return true;
        }
    }
}