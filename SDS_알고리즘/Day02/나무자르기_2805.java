package SDS_알고리즘.Day02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
4 7
20 15 10 17
 */
public class 나무자르기_2805 {
    static int N;
    static int goal;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        goal = Integer.parseInt(st.nextToken());
        trees = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(trees);

        int left = 0;
        int right =  2000000000;
        int mid;
        int result = 0;
        long sum;
        while (true) {
            mid = (left + right) / 2;

            sum = cut(mid);
            if (sum > goal) {
                left = mid + 1;
                result = mid;
            }
            else if (sum == goal) {
                result = mid;
                break;
            }
            else {
                right = mid - 1;
            }

            if (left > right) break;
        }
        System.out.println(result);
    }

    private static long cut(int mid) {
        long sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (trees[i] <= mid) break;
            sum += trees[i] - mid;
        }
        return sum;
    }
}
