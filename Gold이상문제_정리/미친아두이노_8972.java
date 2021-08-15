package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 미친아두이노_8972 {
    static int[][] Map = new int[100][100];
    static int[] dy = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int N, M, moveCnt;
    static Dot I = new Dot(0, 0);
    static ArrayList<Dot> crazyArduinos = new ArrayList<>();

    private static class Dot {
        int y, x;

        public Dot(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'R') {
                    crazyArduinos.add(new Dot(i, j));
                    Map[i][j] = -1;
                }
                if (s.charAt(j) == 'I') I = new Dot(i, j);
            }
        }

        String moveOrder = br.readLine();

        if (!play(moveOrder)) answer.append("kraj ").append(moveCnt);
        else {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (Map[i][j] == -1) answer.append("R");
                    else if (i == I.y && j == I.x) answer.append("I");
                    else answer.append(".");
                }
                answer.append("\n");
            }
        }
        System.out.print(answer);
    }

    private static boolean play(String moveOrder) {

        for (int i = 0; i < moveOrder.length(); i++) {
            moveCnt = i + 1;
            int dir = (moveOrder.charAt(i) - '0') - 1;

            I.y += dy[dir];
            I.x += dx[dir];

            if (Map[I.y][I.x] == -1) return false;
            if (!meetCrazyArduino()) return false;
        }
        return true;
    }

    private static boolean meetCrazyArduino() {
        int iY = I.y;
        int iX = I.x;
        for (Dot arduino : crazyArduinos) {
            Map[arduino.y][arduino.x] += 1;
            int Min = 1000;
            int dir = 0;
            int dist;
            for (int d = 0; d < 9; d++) {
                dist = Math.abs(iY - (arduino.y + dy[d])) + Math.abs(iX - (arduino.x + dx[d]));
                Min = Math.min(dist, Min);
                if (dist == Min) dir = d;
            }

            arduino.y += dy[dir];
            arduino.x += dx[dir];
            Map[arduino.y][arduino.x] -= 1;

            if (arduino.y == iY && arduino.x == iX) return false;
        }

        crazyArduinos.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Map[i][j] < -1) Map[i][j] = 0;
                else if (Map[i][j] == -1) crazyArduinos.add(new Dot(i, j));
            }
        }
        return true;
    }
}
