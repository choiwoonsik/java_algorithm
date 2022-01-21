package SDS_알고리즘.Day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
5
10 5
10 7
1337 23
123454321 42
999999937 142857133
 */
public class 캔디분배_3955 {
    static int N;
    static final int C = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            // 1. 해 검증
            EGRsult egcd = egcd(A, B);
            long GCD = egcd.r;
            if (GCD != 1) System.out.println("IMPOSSIBLE");
            else {
                // 2. 초기 해 구하기
                // x0 = s * C / GCD;
                // y0 = t * C / GCD;
                long x0 = egcd.s * C / GCD;
                long y0 = egcd.t * C / GCD;

                // 3. 일반 해 구하기
                // x = x0 + B / GCD * K;
                // y = y0 - A / GCD * K;

                // 4. K의 범위
                // x < 0
                // x0 + B * k < 0
                // k < - x0 / B

                // 0 < y <= 1e9
                // 0 < y0 - A * k <= 1e9
                // - y0 < - A * k <= 1e9 - y0

                //                     k < - x0 / B
                // ( y0 - 1e9) / A  <= k < y0 / A

                long kFromX = (long) (Math.ceil((double) -x0 / (double) B)) - 1;
                long kFromY = (long) (Math.ceil((double) y0 / (double) A)) - 1;
                long k = Math.min(kFromX, kFromY);
                long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);
                if (k >= kLimitFromY) {
                    System.out.println(y0 - A / GCD * k);
                } else
                    System.out.println("IMPOSSIBLE");
            }
        }
    }

    private static EGRsult egcd(long a, long b) {
        long s0 = 1;
        long t0 = 0;
        long r0 = a;

        long s1 = 0;
        long t1 = 1;
        long r1 = b;

        while (r1 != 0) {
            long tmp;
            long q = r0 / r1;

            tmp = r0 - (q * r1);
            r0 = r1;
            r1 = tmp;

            tmp = s0 - (q * s1);
            s0 = s1;
            s1 = tmp;

            tmp = t0 - (q * t1);
            t0 = t1;
            t1 = tmp;
        }
        return new EGRsult(s0, t0, r0);
    }

    private static class EGRsult {

        long s, t, r;

        public EGRsult(long s, long t, long r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }
    }
}
