package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최대공약수하나빼기_14476 {
    static int N;
    static int[] A;
    static int[] left_GCD;
    static int[] right_GCD;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        left_GCD = new int[N];
        right_GCD = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        // 왼쪽 gcd 배열 채우기
        left_GCD[0] = A[0];
        for (int i = 1; i < N; i++) {
            left_GCD[i] = gcd(left_GCD[i -1], A[i]);
        }

        // 오른쪽 gcd 배열 채우기
        right_GCD[N-1] = A[N-1];
        for (int i = N - 2; i >= 0; i--) {
            right_GCD[i] = gcd(right_GCD[i + 1], A[i]);
        }

        int maxGCD = -1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            // 0번째를 제거하면
            if (i == 0) {
                if (right_GCD[i + 1] > maxGCD)
                    maxGCD = right_GCD[i + 1];
            }
            // 마지막꺼를 제거하면
            else if (i == N -1) {
                if (left_GCD[i - 1] > maxGCD) {
                    index = i;
                    maxGCD = left_GCD[i - 1];
                }
            }
            else {
                if (gcd(left_GCD[i - 1], right_GCD[i + 1]) > maxGCD) {
                    maxGCD = gcd(left_GCD[i - 1], right_GCD[i + 1]);
                    index = i;
                }
            }
        }

        if (A[index] % maxGCD == 0)
            System.out.println(-1);
        else
            System.out.println(maxGCD + " " + A[index]);
    }

    private static int gcd (int a, int b)
    {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
