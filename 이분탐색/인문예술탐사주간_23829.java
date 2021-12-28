package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인문예술탐사주간_23829 {
    static int N, Q;
    static int[] treePos;
    static long[] cumulativeSum;

    public static void main(String[] args) throws IOException {
        // 누적합
        // 이분탐색

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        treePos = new int[N + 1];
        cumulativeSum = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            treePos[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(treePos);
        for (int i = 1; i <= N; i++) {
            if (i == 1) cumulativeSum[i] = treePos[i];
            else cumulativeSum[i] = cumulativeSum[i - 1] + treePos[i];
        }

        for (int i = 0; i < Q; i++) {
            long dist = Integer.parseInt(br.readLine());
            int mid = lowerBound(dist);

            long leftSum;
            long rightSum;

            if (treePos[mid] <= dist) { // 현재 나무가 작거나 같은 경우
                leftSum = (mid * dist) - cumulativeSum[mid];
                rightSum = (cumulativeSum[N] - cumulativeSum[mid]) - ((long) (N - mid) * dist);
            } else { // 현재 나무가 큰 경우
                leftSum = ((mid - 1) * dist) - cumulativeSum[mid - 1];
                rightSum = (cumulativeSum[N] - cumulativeSum[mid - 1]) - ((long) (N - mid + 1) * dist);
            }

            answer.append((leftSum + rightSum)).append("\n");
        }
        System.out.print(answer);
    }

    private static int lowerBound(long pos) {
        int left = 1;
        int right = N;
        int mid = 0;

        while (left <= right) {
            mid = (left + right) / 2;

            if (treePos[mid] > pos)
                right = mid - 1;
            else if (treePos[mid] < pos)
                left = mid + 1;
            else
                break;
        }
        return mid;
    }
}
