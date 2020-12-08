public class programmers_프렌즈4블록 {
    static char[] kakaoArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static char[][] map;
    static char[][] subMap;
    static boolean[][] vst;
    static int[] dy = {1, 1, 0};
    static int[] dx = {0, 1, 1};
    static boolean stop;
    static int countDestroy;
    public static void main(String[] args)
    {
        String[] arr = {"IIIIOO", "IIIOOO", "IIIOOI", "IOOIII", "OOOIII", "OOIIII"};
        solution(6, 6, arr); //32
    }
    public static int solution(int m, int n, String[] board) {
        map = new char[m][n];
        subMap = new char[m][n];

        for (int i = 0; i < m ; i++) {
            map[i] = board[i].toCharArray();
            subMap[i] = board[i].toCharArray();
        }

        while (true) {
            stop = true;
            Destroy(m, n);
            Copy(m);
            if (stop) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (map[i][j] == ' ')
                            countDestroy++;
                    }
                }
                break;
            }
            Down(m, n);
            BackUp(m);
        }

        return countDestroy;
    }

    private static void Copy(int m) {
        for (int h = 0; h < m; h++)
            map[h] = subMap[h].clone();
    }

    private static void BackUp(int m){
        for (int h = 0; h < m; h++)
            subMap[h] = map[h].clone();
    }

    private static void Destroy(int m, int n) {
        for (int kakao = 0; kakao < kakaoArr.length; kakao++) {
            vst = new boolean[m][n];
            for (int h = 0; h < m; h++) {
                for (int w = 0; w < n; w++) {
                    if (map[h][w] == kakaoArr[kakao] && !vst[h][w]) {
                        vst[h][w] = true;
                        Check(h, w, kakao, m, n);
                    }
                }
            }
        }
    }

    private static void Check(int h, int w, int kakao, int m, int n) {
        int count = 1;
        for (int dir = 0; dir < 3; dir++)
        {
            int y = dy[dir] + h;
            int x = dx[dir] + w;
            if (y >= 0 && y < m && x >= 0 && x < n) {
                if (!vst[y][x] && map[y][x] == kakaoArr[kakao])
                    count++;
            }
        }
        if (count == 4)
        {
            stop = false;
            subMap[h][w] = ' ';
            for (int d = 0; d < 3; d++){
                int y = dy[d] + h;
                int x = dx[d] + w;
                subMap[y][x] = ' ';
            }
        }
        return;
    }

    private static void Down(int m, int n) {
        for (int line = m - 1; line > 0; line--) {
            for (int w = 0; w < n; w++) {
                if (map[line][w] == ' ') {
                    for (int st = line - 1; st >= 0; st--) {
                        if (map[st][w] != ' ') {
                            map[line][w] = map[st][w];
                            map[st][w] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }
}
