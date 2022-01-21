package SDS_알고리즘.Day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
7 4 47
 */
public class 사전_1256 {
    static int Max = (int) 1e9;
    static int A, Z, K;
    static int total;
    static int[][] pascalTriangle;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        total = A + Z;
        pascalTriangle = new int[total + 1][total + 1];

        makePascalTriangle();
        if (pascalTriangle[total][Z] < K) {
            System.out.println(-1);
        } else {
            combination(total, Z, K);
        }
        System.out.println(answer);
    }

    private static void combination(int n, int r, int k) {
        if (n == 0) return;

        if (r == 0) {
            answer.append("a");
            combination(n - 1, r, k);
        } else if (k <= pascalTriangle[n - 1][r]) {
            // a
            answer.append("a");
            combination(n - 1, r, k);
        } else {
            // z
            answer.append("z");
            combination(n - 1, r - 1, k - pascalTriangle[n - 1][r]);
        }
    }

    private static void makePascalTriangle() {
        for (int n = 1; n <= total; n++) {
            for (int r = 1; r <= n; r++) {
                if (r == 1) pascalTriangle[n][r] = n;
                else pascalTriangle[n][r] = pascalTriangle[n - 1][r - 1] + pascalTriangle[n - 1][r];
                if (pascalTriangle[n][r] > Max) pascalTriangle[n][r] = Max;
            }
        }
    }
}
