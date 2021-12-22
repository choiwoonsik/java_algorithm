package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간나누기2_13397 {
    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch();
    }

    private static void binarySearch() {
        int divCount;
        int left = 0;
        int right = 10001;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            divCount = divide(0, 0, 10001, 0, mid);
            if (divCount <= M) right = mid - 1;
            if (divCount > M) left = mid + 1;
        }
        System.out.println(left);
    }

    private static int divide(int end, int max, int min, int divCount, int diff) {
        if (end == N) return divCount + 1;
        max = Math.max(max, nums[end]);
        min = Math.min(min, nums[end]);
        int tmpDiff = max - min;
        if (tmpDiff <= diff)
            return divide(end + 1, max, min, divCount, diff);
        else
            return divide(end, 0, 10001, divCount + 1, diff);
    }
}
