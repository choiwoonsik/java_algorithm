import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
5
X S X X T
T X S X X
X X X X X
X T X X X
X X T X X
4
X S X T
X X S X
X X X X
T T T X
5
X S S S X
T X X S X
X T X S X
X X T X S
X X X T X
5
X X S X X
X X X X X
S X T X S
X X X X X
X X S X X
 */
public class 감시피하기_18428 {
    static int N;
    static boolean isPossible;
    static char[][] map;
    static ArrayList<Dot> teachers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        teachers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char type = st.nextToken().charAt(0);
                map[i][j] = type;
                if (map[i][j] == 'T') teachers.add(new Dot(i, j));
            }
        }

        backTracking(0, 0, 0);
        if (isPossible) System.out.println("YES");
        else System.out.println("NO");
    }

    private static void backTracking(int r, int c, int depth) {
        if (isPossible) return;
        if (depth == 3) {
            if (possible()) isPossible = true;
            return;
        }

        int j = c;
        for (int i = r; i < N; i++) {
            for (; j < N; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    backTracking(i, j + 1, depth + 1);
                    map[i][j] = 'X';
                }
            }
            j = 0;
        }
    }

    private static boolean possible() {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        boolean possible = true;

        for (Dot teacher : teachers) {
            int ny;
            int nx;

            for (int d = 0; d < 4; d++) {
                ny = teacher.y;
                nx = teacher.x;
                while (true) {
                    ny += dy[d];
                    nx += dx[d];

                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) break;
                    if (map[ny][nx] == 'O' || map[ny][nx] == 'T') break;
                    if (map[ny][nx] == 'S') return false;
                }
            }
        }
        return possible;
    }

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
