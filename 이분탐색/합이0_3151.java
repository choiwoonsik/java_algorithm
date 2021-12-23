package 이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
/*
2 -5 2 3 -4 7 -4 0 1 -6

-6 -5 -4 -4 0 1 2 2 3 7

 */
public class 합이0_3151 {
    static int N;
    static int[] sortMember;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sortMember = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String info = st.nextToken();
            sortMember[i] = Integer.parseInt(info);
        }
        Arrays.sort(sortMember);

        long total = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                int target = -(sortMember[i] + sortMember[j]);
                int ldx = lowerBound(j + 1, target);
                if (ldx == N || sortMember[ldx] != target) continue;
                int udx = upperBound(j + 1, target);
                total += udx - ldx;
            }
        }

        System.out.println(total);
    }

    private static int lowerBound(int left, int target) {
        int right = N;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (sortMember[mid] >= target) {
                right = mid; // 크다, 줄임
            }
            else {
                left = mid + 1; // 작다, 늘림
            }
        }
        return right;
    }

    private static int upperBound(int left, int target) {
        int right = N;
        int mid;

        while (left < right) {
            mid = (left + right) / 2;

            if (sortMember[mid] <= target) {
                left = mid + 1; // 작다, 늘림
            }
            else {
                right = mid; // 크다, 줄임
            }
        }
        return right;
    }
}
