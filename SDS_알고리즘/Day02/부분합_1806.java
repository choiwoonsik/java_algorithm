package SDS_알고리즘.Day02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
10 15
5 1 3 5 10 7 4 9 2 8

10 10
1 1 1 1 1 1 1 1 1 10

10 12
12 12 12 12 12 12 12 12 12 12
 */
public class 부분합_1806 {
    static int N, G;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int MinLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        long sum = 0;
        while (right <= N) {
            sum += nums[right];

            while (sum >= G) {
                MinLen = Math.min(MinLen, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }

        if (MinLen == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(MinLen);
    }
}
