package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
첫 번째 줄에 독서실 좌석의 개수 N, 독서실 예약자 수 T, 민규가 좋아하는 좌석 번호 P 가 공백으로 구분되어 주어진다.
(1 ≤ N ≤ 100, 1 ≤ T ≤ 500, 1 ≤ P ≤ N)
다음 T 개의 줄에는 독서실 입실 시간, 독서실 퇴실 시간이 HHMM HHMM 형태로 입력된다.
(0900 ≤ HHMM ≤ 2100, 0910 0900와 같이 퇴실 시간이 입실 시간보다 빠른 경우는 없다)

사람들은 가장 가까이에 앉아있는 사람이 가장 먼 자리를 선호한다. 만약 독서실을 이용하는 사람이 없다면 좌석번호 1번 자리를 가장 선호한다.
1번 규칙으로 비교할 수 없다면, 가장 먼 좌석들 중에서 좌석 번호가 가장 작은 자리를 선호한다.

5 6 1
0915 0930
0940 2040
0910 0920
2040 2050
2043 2047
2044 2046
 */
public class 독서실_거리두기_20665 {
    static int N, T, P;
    static ArrayList<TimePair> tList = new ArrayList<>();
    static boolean[][][] isSeated = new boolean[24][60][101]; // h, m, seat
    public static void main(String[] args) throws IOException {
        input();
        int answer = solve();

        System.out.println(answer);
    }

    private static int solve() {
        for (TimePair time : tList) {
            int startH = time.start.h;
            int startM = time.start.m;
            int endH = time.end.h;
            int endM = time.end.m;
            int seat = findSeat(startH, startM);

            for (int H = startH; H <= endH; H++) {

                if (startH == endH) {
                    for (int M = startM; M < endM; M++) {
                        isSeated[H][M][seat] = true;
                    }
                    break;
                }

                if (H == startH) {
                    for (int M = startM; M < 60; M++) {
                        isSeated[H][M][seat] = true;
                    }
                } else if (H == endH) {
                    for (int M = 0; M < endM; M++) {
                        isSeated[H][M][seat] = true;
                    }
                } else {
                    for (int M = 0; M < 60; M++) {
                        isSeated[H][M][seat] = true;
                    }
                }
            }
        }

        return checkMyTime();
    }

    private static int checkMyTime() {
        int totalMin = 0;
        for (int H = 9; H < 21; H++) {
            for (int M = 0; M < 60; M++) {
                if (!isSeated[H][M][P]) totalMin += 1;
            }
        }

//        for (int h = 9; h < 21; h++) {
//            for (int m = 0; m < 60; m++) {
//                System.out.print(h + ":" + m + "> ");
//                for (int i = 1; i <= N; i++) {
//                    if (!isSeated[h][m][i]) System.out.print("[ ]");
//                    else System.out.print("[X]");
//                }
//                System.out.println();
//            }
//        }
        return totalMin;
    }

    private static int findSeat(int h, int m) {
        int maxDist = 0;
        int pos = -1;

        for (int i = 1; i <= N; i++) {
            // 비어있는 자리면 체크
            if (!isSeated[h][m][i]) {
                int dist = calcDist(h, m, i);
                if (dist > maxDist) {
                    maxDist = dist;
                    pos = i;
                }
            }
        }

        return pos;
    }

    private static int calcDist(int h, int m, int i) {
        int midDist = 101;
        for (int next = 1; next <= N; next++) {
            if (next == i) continue;
            // next에 사람이 있다면 거리 확인
            if (isSeated[h][m][next]) {
                int d = Math.abs(i - next);
                if (d < midDist) {
                    midDist = d;
                }
            }
        }

        return midDist;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            TIME startT = new TIME(start / 100, start % 100);
            TIME endT = new TIME(end / 100, end % 100);
            tList.add(new TimePair(startT, endT));
        }
        tList.sort((t1, t2) -> {
            if (t1.start.h == t2.start.h) {
                if (t1.start.m == t2.start.m) {
                    if (t1.end.h == t2.end.h) {
                        return Integer.compare(t1.end.m, t2.end.m);
                    }
                    return Integer.compare(t1.end.h, t2.end.h);
                }
                return Integer.compare(t1.start.m, t2.start.m);
            }
            return Integer.compare(t1.start.h, t2.start.h);
        });
    }

    private static class TimePair {
        TIME start;
        TIME end;

        public TimePair(TIME start, TIME end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class TIME {
        int h;
        int m;

        public TIME(int h, int m) {
            this.h = h;
            this.m = m;
        }

        @Override
        public String toString() {
            return h + ":" + m;
        }
    }
}
