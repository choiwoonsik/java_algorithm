package SDS_알고리즘.Day03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 커피숖2_1275 {
    static int N;
    static int Q;
    static int S;
    static long[] numbers;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        S = 1;
        while (S < N) {
            S *= 2;
        }

        numbers = new long[2 * S];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[S + i] = Integer.parseInt(st.nextToken());
        }

        init();

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            int newVal = Integer.parseInt(st.nextToken());
            long val;

            val = (left < right
                    ? calculate(1, S, 1, left, right)
                    : calculate(1, S, 1, right, left));
            answer.append(val).append("\n");

            update(index, newVal);
        }
        System.out.print(answer);
    }

    private static void update(int index, int newVal) {
        int node = index + S - 1;
        numbers[node] = newVal;

        while (node > 1) {
            node /= 2;
            numbers[node] = numbers[node * 2] + numbers[node * 2 + 1];
        }
    }

    private static long calculate(int left, int right, int node, int queryLeft, int queryRight) {
        if (right < queryLeft || queryRight < left) return 0;

        if (left == right) return numbers[node];

        if (queryLeft <= left && right <= queryRight) return numbers[node];

        int mid = (left + right) / 2;
        return calculate(left, mid, node * 2, queryLeft, queryRight)
                + calculate(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
    }

    private static void init() {
        for (int i = S - 1; i >= 1; i--) {
            numbers[i] = numbers[i * 2] + numbers[i * 2 + 1];
        }
    }
}
