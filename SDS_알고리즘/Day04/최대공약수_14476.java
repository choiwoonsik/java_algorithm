package SDS_알고리즘.Day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
5
8 12 24 36 48
 */

public class 최대공약수_14476 {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftGcd = new int[N];
        int[] rightGcd = new int[N];
        for (int i = 0; i < N; i++) {
            if (i == 0) leftGcd[i] = nums[0];
            else leftGcd[i] = gcd(leftGcd[i - 1], nums[i]);
        }
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1) rightGcd[i] = nums[N - 1];
            else rightGcd[i] = gcd(rightGcd[i + 1], nums[i]);
        }

        int maxGCD = 0;
        int maxK = 0;
        for (int i = 0; i < N; i++) {
            int max = 0;
            int K = nums[i];
            if (i == 0) {
                max = Math.max(max, rightGcd[1]);
            } else if (i == N - 1) {
                max = Math.max(max, leftGcd[N - 2]);
            } else {
                max = Math.max(max, gcd(leftGcd[i - 1], rightGcd[i + 1]));
            }
            if (max > maxGCD) {
                maxGCD = max;
                if (maxGCD % K == 0) maxGCD = -1;
                else maxK = K;
            }
        }
        if (maxK % maxGCD == 0) System.out.println(-1);
        else System.out.println(maxGCD + " " + maxK);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
}
