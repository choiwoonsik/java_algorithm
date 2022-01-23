package SDS_알고리즘.Day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 순열의순서_1722 {
    static int N;
    static long[] dp;
    static StringBuilder ret = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            nums.add(i);
        }
        makeFactorial();

        st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());
        if (type == 1) {
            long index = Long.parseLong(st.nextToken());
            findNums(N, index, nums);
            System.out.println(ret);
        } else {
            Queue<Integer> orders = new LinkedList<>();
            while (st.hasMoreTokens()) {
                orders.add(Integer.parseInt(st.nextToken()));
            }
            long answer = findIndex(N, 0, orders, nums);
            System.out.println(answer);
        }
    }

    private static long findIndex(int n, long index, Queue<Integer> orders, LinkedList<Integer> nums) {
        if (orders.size() == 0)
            return index + 1;

        int i;
        int next = orders.poll();
        for (i = 0; i < nums.size(); i++) {
            if (nums.get(i) == next) {
                nums.remove(i);
                break;
            }
        }

        return findIndex(n - 1, index + dp[n - 1] * i, orders, nums);
    }

    private static void findNums(int n, long index, LinkedList<Integer> nums) {
        if (n == 1) {
            ret.append(nums.get(0));
            return;
        }

        int i;
        for (i = 1; i <= n; i++) {
            if (index <= i * dp[n - 1]) {
                break;
            }
        }
        ret.append(nums.remove(i - 1)).append(" ");

        findNums(n - 1, index - dp[n-1] * (i - 1), nums);
    }

    private static void makeFactorial() {
        dp = new long[21];
        dp[1] = 1;

        for (int i = 2; i < 21; i++) {
            dp[i] = i * dp[i - 1];
        }
    }
}
