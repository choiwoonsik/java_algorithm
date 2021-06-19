package Silver이하문제_모음;

public class programmers_프렌즈4블록2 {
    static int[] dy = {-1, -1, 0};
    static int[] dx = {0, 1, 1};
    static char[][] map;
    static char[][] submap;
    static boolean[][] visited;
    static boolean bbomCheck;
    public static void main(String[] args) {
        String[] arr = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        solution(6, 6, arr);
    }
    private static int solution(int m, int n, String[] board)
    {
        map = new char[m][n];
        submap = new char[m][n];
        visited = new boolean[m][n];
        int answer;

        for (int h = 0; h < m; h++) {
            String floor = board[h];
            for (int w = 0; w < n; w++) {
                map[h][w] = floor.charAt(w);
                submap[h][w] = floor.charAt(w);
            }
        }

        while(true)
        {
            //System.out.println("check");
            bbomCheck = false;
            // 부수기
            bbom(m, n);
            map_copy(m);
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            if (!bbomCheck)
                break;
            // 내리기
            down(m, n);
            map_copy_tosub(m);
        }
        answer = count_blank(m, n);
        System.out.println(answer);
        return answer;
    }

    private static int count_blank(int m, int n) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == ' ')
                    count++;
            }
        }
        return count;
    }

    private static void map_copy_tosub(int m) {
        for (int i = 0; i < m; i++)
            submap[i] = map[i].clone();
    }

    private static void down(int m, int n) {
        for (int h = m - 1; h > 0; h--)
        {
            for (int w = 0; w < n; w++) {
                if (map[h][w] == ' ') {
                    for (int nowH = h - 1; nowH >= 0; nowH--) {
                        if (map[nowH][w] != ' ') {
                            map[h][w] = map[nowH][w];
                            map[nowH][w] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void map_copy(int m) {
        for (int i = 0; i < m; i++)
            map[i] = submap[i].clone();
    }

    private static void bbom(int m, int n) {
        for (int h = 0; h < m; h++) {
            for (int w = 0; w < n; w++) {
                int count = 1;
                char now = map[h][w];
                for (int dir = 0; dir < 3; dir++) {
                    int y = dy[dir] + h;
                    int x = dx[dir] + w;
                    if (y >= 0 && y < m && x >= 0 && x < n) {
                        if (map[y][x] == now && now != ' ')
                            count++;
                    }
                    if (count == 4) {
                        bbomCheck = true;
                        submap[h][w] = ' ';
                        for (int d = 0; d < 3; d++)
                            submap[dy[d] + h][dx[d] + w] = ' ';
                    }
                }
            }
        }
    }
}
