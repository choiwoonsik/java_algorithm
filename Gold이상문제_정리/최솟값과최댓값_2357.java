package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 최솟값과최댓값_2357 {
    static int[] A;
    static int[] treeMax;
    static int[] treeMin;
    static int height, last, N, M;
    public static void main(String[] args) throws IOException {
        // a번 정수부터 b번 정수까지 중에서 가장 작은 정수 또는 가장 큰 정수 찾기 -> O(log N)으로
        // N, M : N개의 수 , M개의 범위

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder str = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        treeMax = new int[N*4];
        treeMin = new int[N*4];
        Arrays.fill(treeMin, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
        }

        height = (int) Math.ceil(log(N, 2));
        last = (int) Math.pow(2, height);

        initMax(1, 1, last);
        initMin(1, 1, last);

        for (int i = 0; i < M; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min_val = min(1, 1, last, a, b);
            int max_val = max(1, 1, last, a, b);
            str.append(min_val).append(" ").append(max_val).append("\n");
        }
        System.out.print(str);
    }

    private static int min(int index, int start, int end, int left, int right) {
        if (start > right || end < left) return 1234567890;
        else if (left <= start && end <= right) {
            return treeMin[index];
        }
        else {
            int mid = (start+end)/2;
            return Math.min(min(index*2, start, mid, left, right), min(index*2+1, mid+1, end, left, right));
        }
    }

    private static int max(int index, int start, int end, int left, int right) {
        if (start > right || end < left) return 0;
        else if (left <= start && end <= right) {
            return treeMax[index];
        }
        else {
            int mid = (start+end)/2;
            return Math.max(max(index*2, start, mid, left, right), max(index*2+1, mid+1, end, left, right));
        }
    }

    private static int initMin(int index, int start, int end) {
        if (start > N) return 987654321;
        else if (start == end)
            treeMin[index] = A[start];
        else {
            int mid = (start + end) / 2;
            treeMin[index] = Math.min(initMin(index*2, start, mid), initMin(index*2+1, mid+1, end));
        }
        return treeMin[index];
    }

    private static int initMax(int index, int start, int end) {
        if (start > N) return 0;
        else if (start == end)
            treeMax[index] = A[start];
        else {
            int mid = (start + end) / 2;
            treeMax[index] = Math.max(initMax(index*2, start, mid), initMax(index*2+1, mid+1, end));
        }
        return treeMax[index];
    }

    private static double log(int n, int base) {
        return (Math.log10(n) / Math.log10(base));
    }
}
