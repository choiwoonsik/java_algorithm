package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_2042 {
    static int N, M, K, S;
    static long[] numbers;
    static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N) {
            S *= 2;
        }

        numbers = new long[2 * S];
        for (int i = 0; i < N; i++) {
            numbers[S + i] = Long.parseLong(br.readLine());
        }

        init();

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int index = Integer.parseInt(st.nextToken());
                long newVal = Long.parseLong(st.nextToken());
                update(index, newVal);
            } else if (type == 2) {
                int queryLeft = Integer.parseInt(st.nextToken());
                int queryRight = Integer.parseInt(st.nextToken());
                ans.append(calculate(1, S, 1, queryLeft, queryRight)).append("\n");
            }
        }
        System.out.print(ans);
    }

    private static void update(int index, long newVal) {
        int node = S + index - 1;
        numbers[node] = newVal;

        while (node > 1) {
            node /= 2;
            numbers[node] = numbers[node * 2] + numbers[node * 2 + 1];
        }
    }

    private static long calculate(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return 0;

        if (left == right) {
            return numbers[node];
        }

        if (queryLeft <= left && right <= queryRight) {
            return numbers[node];
        }

        int mid = (left + right) / 2;
        return calculate(left, mid, node * 2, queryLeft, queryRight) +
                calculate(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
    }

    private static void init() {
        for (int i = S - 1; i >= 1; i--) {
            numbers[i] = numbers[i * 2] + numbers[i * 2 + 1];
        }
    }
}
