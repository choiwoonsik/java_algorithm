package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
5
3 6 10 1 12
1 2 3 34
 */
public class 제기차기_23830 {
    static int N;
    static int[] point;
    static int p;
    static int q;
    static int r;
    static long S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        point = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken());

        binarySearch();
    }

    private static void binarySearch() {
        int left = 1;
        int right = 110000;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            long avg = calc(mid);
            if (avg < S)
                left = mid + 1; // 평균 높여야 댐. -> K 키움
            else {
                right = mid; // 최대한 낮춘다. -> K 낮춤
            }
        }
        if (right == 110000) System.out.println(-1);
        else System.out.println(right);
    }

    private static long calc(int mid) {
        long sum = 0;
        for (int myPoint : point) {
            if (myPoint > mid + r) sum += myPoint - p;
            else if (myPoint < mid) sum += myPoint + q;
            else sum += myPoint;
        }
        return sum;
    }
}
